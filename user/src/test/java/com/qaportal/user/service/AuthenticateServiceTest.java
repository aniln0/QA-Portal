package com.qaportal.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import com.qaportal.user.dto.UserLoginDTO;
import com.qaportal.user.entity.UserEntity;
import com.qaportal.user.jwt.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthenticateServiceTest {

	@Mock
	private UserService userService;

	@Mock
	private JwtUtil jwtUtil;
	
	@InjectMocks
	private AuthenticateService authenticateService;
	
	private UserLoginDTO testuserloginDTO;
	
	private UserEntity testuserEntity;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(authenticateService);
		
		testuserEntity = new UserEntity();
		testuserEntity.setUserid(1);
		testuserEntity.setFirstname("Edward");
		testuserEntity.setLastname("Norton");
		testuserEntity.setEmailid("edward@gmail.com");
		testuserEntity.setPassword("Edward@1234");
		testuserEntity.setCreatedate(new Date());
	
		testuserloginDTO = new UserLoginDTO();
		testuserloginDTO.setEmailid("edward@gmail.com");
		testuserloginDTO.setPassword("Edward@1234");
		
	}
	
	@Test
	public void authenticateSuccessfulTest() {
		
		when(userService.findByemailid(testuserloginDTO.getEmailid())).thenReturn(testuserEntity);
		when(userService.checkPass(testuserEntity.getPassword(), testuserloginDTO.getPassword())).thenReturn(true);
		when(jwtUtil.generateToken(testuserloginDTO.getEmailid())).thenReturn("jwt_token");
		
		String authenticate = authenticateService.authenticate(testuserloginDTO);
		
		assertEquals("jwt_token", authenticate);
	}
	
	@Test
	public void authenticateUnsuccessfulTest() {
		
		when(userService.findByemailid(testuserloginDTO.getEmailid())).thenReturn(testuserEntity);
		when(userService.checkPass(testuserEntity.getPassword(), testuserloginDTO.getPassword())).thenReturn(false);
		
		String authenticate = authenticateService.authenticate(testuserloginDTO);
		
		assertEquals("Invalid Password", authenticate);
	}
	
	@Test
	public void getUsernameFromTokenTest() {
		
		when(jwtUtil.extractUsername("jwt_token123456")).thenReturn("anurag@gmail.com");
		
		String usernameFromToken = authenticateService.getUsernameFromToken("jwt_token");
	
	}
}
