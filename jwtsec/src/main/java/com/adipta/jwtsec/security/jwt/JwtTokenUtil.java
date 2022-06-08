package com.adipta.jwtsec.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adipta.jwtsec.security.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil 
{
	private static final long EXPIRE_DURATION=24*60*60*1000;//24h
		
	@Value(value = "${app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(User user)
	{
		return Jwts.builder()
					.setSubject(user.getId()+","+user.getEmail())
					.setIssuer("Adipta")
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+this.EXPIRE_DURATION))
					.signWith(SignatureAlgorithm.HS512, this.secretKey)
					.compact();
	}
	
	public boolean validateAccessToker(String token)
	{
		try
		{
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}
		catch(ExpiredJwtException ex)
		{
			System.out.println("JWT Expired "+ex.getMessage());
		}
		catch(IllegalArgumentException ex)
		{
			System.out.println("Token is null, empty or has only whitespace:"+ex.getMessage());
		}
		catch(MalformedJwtException ex)
		{
			System.out.println("JWT is invalid "+ex.getMessage());
		}
		catch(Exception ex)
		{
			System.out.println("Got an exception "+ex.getMessage());
		}
		return false;
	}
	
	public String getSubject(String token)
	{
		return parseClaims(token).getSubject();
	}
	
	private Claims parseClaims(String token)
	{
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
}