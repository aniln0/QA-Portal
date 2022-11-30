package com.qaportal.user.jwt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.qaportal.user.entity.UserEntity;
import com.qaportal.user.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomUserDetailsServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	private UserEntity userEntity;
	
	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.initMocks(customUserDetailsService);
		
		userEntity = new UserEntity(1, "fname", "lname", "user@gmail.com", "pass", new Date());
		
	}
	
	@Test
	public void loadUserByUsernameTest() {
		
		when(userRepository.findByemailid("user@gmail.com")).thenReturn(userEntity);
		
		User userByUsername = customUserDetailsService.loadUserByUsername("user@gmail.com");
		
		assertEquals("pass", userByUsername.getPassword());
	}

}
