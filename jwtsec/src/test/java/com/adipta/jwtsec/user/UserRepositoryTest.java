package com.adipta.jwtsec.user;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.adipta.jwtsec.security.dao.UserRepository;
import com.adipta.jwtsec.security.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)//doesn't roll back the changes
public class UserRepositoryTest 
{
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testCreateUser()
	{
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		
		String rawPassword="password";
		
		String encodedPassword=passwordEncoder.encode(rawPassword);
		
		User newUser=new User();
		
		newUser.setEmail("adiptabasu28@gmail.com");
		newUser.setPassword(encodedPassword);
		
		User savedUser=userRepository.save(newUser);
		
		assertNotNull(savedUser);
		assertNotEquals(savedUser.getId(), 0);
	}
}