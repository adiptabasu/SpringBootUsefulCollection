package com.adipta.jwtsec.security.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USERS")
public class User implements UserDetails//UserDetails is a Spring interface.
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "EMAIL",nullable = false,unique = true,length = 50)
	private String email;
	
	@Column(name = "PASSWORD",nullable = false,length = 64)
	private String password;

	public User(Integer id, String email, String password) 
	{
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public User()
	{
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	@Override
	public String toString() 
	{
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() 
	{
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked() 
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isEnabled() 
	{
		return true;
	}
	
}