package com.adipta.jwtsec.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adipta.jwtsec.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	Optional<User>findByEmail(String email);
}