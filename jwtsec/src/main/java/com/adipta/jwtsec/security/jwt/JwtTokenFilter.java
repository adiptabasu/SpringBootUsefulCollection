package com.adipta.jwtsec.security.jwt;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adipta.jwtsec.security.entity.User;

@Component
public class JwtTokenFilter extends OncePerRequestFilter 
{
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		if(!hasAuthorizationHeader(request))
		{
			filterChain.doFilter(request, response);
			return;
		}
		String accessToken=getAccessToken(request);
		
		if(!jwtTokenUtil.validateAccessToker(accessToken))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		setAuthenticationContext(accessToken,request);
		filterChain.doFilter(request, response);
	}
	
	private void setAuthenticationContext(String accessToken, HttpServletRequest request) 
	{
		UserDetails userDetails=getUserDetails(accessToken);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
			=new UsernamePasswordAuthenticationToken(userDetails, null,null);
		usernamePasswordAuthenticationToken.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request)
				);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

	private UserDetails getUserDetails(String accessToken) 
	{
		User userDetails=new User();
		String subjectArray[]=jwtTokenUtil.getSubject(accessToken).split(",");
		System.out.println(Arrays.toString(subjectArray));
		userDetails.setId(Integer.parseInt(subjectArray[0]));
		userDetails.setEmail(subjectArray[1]);
		return userDetails;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest request)
	{
		String header=request.getHeader("Authorization");
		System.out.println("Auth header is :"+header);
		if(ObjectUtils.isEmpty(header)||!header.startsWith("Bearer"))
		{
			return false;
		}
		return true;
	}
	
	private String getAccessToken(HttpServletRequest request)
	{
		String header=request.getHeader("Authorization");
		String token=header.split(" ")[1].trim();
		System.out.println("Access token:"+token);
		return token;
	}
}