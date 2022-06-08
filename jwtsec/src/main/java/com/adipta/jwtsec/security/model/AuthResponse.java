package com.adipta.jwtsec.security.model;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class AuthResponse 
{
	@Email
	@Length(min = 5,max = 50)
	private String email;
	
	private String authToken;

	public AuthResponse()
	{
	}
	
	public AuthResponse(@Email @Length(min = 5, max = 50) String email, String authToken) 
	{
		this.email = email;
		this.authToken = authToken;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getAuthToken() 
	{
		return authToken;
	}

	public void setAuthToken(String authToken) 
	{
		this.authToken = authToken;
	}	
}