package com.qaportal.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.http.ResponseEntity;

import com.qaportal.user.dto.UserDTO;
import com.qaportal.user.dto.UserLoginDTO;
import com.qaportal.user.entity.UserEntity;
import com.qaportal.user.service.AuthenticateService;
import com.qaportal.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private AuthenticateService authService;
	
	@InjectMocks
	private UserController userController;
	
	private UserDTO testuserDTO;
	
	private UserLoginDTO testuserloginDTO;
	
	private UserEntity testuserEntity;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(userController);
		
		testuserEntity = new UserEntity();
		testuserEntity.setUserid(1);
		testuserEntity.setFirstname("Edward");
		testuserEntity.setLastname("Norton");
		testuserEntity.setEmailid("edward@gmail.com");
		testuserEntity.setPassword("Edward@1234");
		testuserEntity.setCreatedate(new Date());
		
		testuserDTO = new UserDTO();
		testuserDTO.setUserid(1);
		testuserDTO.setFirstname("Edward");
		testuserDTO.setLastname("Norton");
		testuserDTO.setEmailid("edward@gmail.com");
		testuserDTO.setPassword("Edward@1234");
		testuserDTO.setCreatedate(new Date());
		
		testuserloginDTO = new UserLoginDTO();
		testuserloginDTO.setEmailid("edward@gmail.com");
		testuserloginDTO.setPassword("Edward@1234");
		
	}
	
	@Test
	public void userRegistrationSuccessfulTest() {
		
		when(userService.addUser(testuserDTO)).thenReturn(true);
		
		ResponseEntity<Object> registeredUser = userController.registerUser(testuserDTO);
		
		assertNotNull(registeredUser);
		assertEquals("201 CREATED", registeredUser.getStatusCode().toString());
		assertEquals("User registered", registeredUser.getBody());
	
	}
	
	@Test
	public void userAlreadyRegisteredTest() {
	
		when(userService.existsByemailid(testuserDTO.getEmailid())).thenReturn(true);
		
		ResponseEntity<Object> registeredUser = userController.registerUser(testuserDTO);
		
		assertNotNull(registeredUser);
		assertEquals("409 CONFLICT", registeredUser.getStatusCode().toString());
		assertEquals("Email already registered!", registeredUser.getBody());
		
	}
	
	@Test
	public void userRegistrationUnsuccessfulTest() {
			
		when(userService.existsByemailid(testuserDTO.getEmailid())).thenReturn(false);
		
		when(userService.addUser(testuserDTO)).thenReturn(false);
		
		ResponseEntity<Object> registeredUser = userController.registerUser(testuserDTO);
		
		assertNotNull(registeredUser);
		assertEquals("400 BAD_REQUEST", registeredUser.getStatusCode().toString());
		assertEquals("Invalid Password", registeredUser.getBody());
		
	}
	
	@Test
	public void generateTokenSuccessTest() {
		
		when(authService.authenticate(testuserloginDTO)).thenReturn("jwt_token");
		
		ResponseEntity<Object> generatedToken = userController.generateToken(testuserloginDTO);
		
		assertNotNull(generatedToken);
		assertEquals("200 OK", generatedToken.getStatusCode().toString());
		assertEquals("jwt_token", generatedToken.getBody());
		
	}
	
	@Test
	public void generateTokenFailedTest() {
		
		when(authService.authenticate(testuserloginDTO)).thenReturn("Invalid Password");
		
		ResponseEntity<Object> generatedToken = userController.generateToken(testuserloginDTO);
		
		assertNotNull(generatedToken);
		assertEquals("400 BAD_REQUEST", generatedToken.getStatusCode().toString());
		assertEquals("Invalid Password", generatedToken.getBody());
		
	}
	
	@Test
	public void getTokenUsernameTest() {
		
		when(authService.getUsernameFromToken("jwt_token")).thenReturn("edward@gmail.com");
		
		String tokenUsername = userController.getTokenUsername("jwt_token");
		
		assertNotNull(tokenUsername);
		assertEquals("edward@gmail.com", tokenUsername);
		
	}
	
	@Test
	public void getUserIdTest() {
		
		when(userService.findByemailid("edward@gmail.com")).thenReturn(testuserEntity);
		
//		int userId = userController.getUserId("edward@gmail.com", "jwt_token");
		
//		assertEquals(1, userId);
		
	}
	
	@Test
	public void getUsernameFromUserId() {
		
		when(userService.findUserByUserId(1)).thenReturn("edward@gmail.com");
		
//		String usernameFromUserId = userController.getUsernameFromUserId(1, "jwt_token");
		
//		assertEquals("edward@gmail.com", usernameFromUserId);
	}
}
