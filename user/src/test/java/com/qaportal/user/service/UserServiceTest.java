package com.qaportal.user.service;

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
import org.modelmapper.ModelMapper;

import com.qaportal.user.dto.UserDTO;
import com.qaportal.user.entity.UserEntity;
import com.qaportal.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapper modelMapper;
	
	private UserDTO testuserdto;
	
	private UserEntity testuserentity;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(userService);
		
		testuserentity = new UserEntity();
		testuserentity.setUserid(1);
		testuserentity.setFirstname("Edward");
		testuserentity.setLastname("Norton");
		testuserentity.setEmailid("edward@gmail.com");
		testuserentity.setPassword("Edward@1234");
		testuserentity.setCreatedate(new Date());

		testuserdto = new UserDTO();
		testuserdto.setUserid(1);
		testuserdto.setFirstname("Edward");
		testuserdto.setLastname("Norton");
		testuserdto.setEmailid("edward@gmail.com");
		testuserdto.setPassword("Edward@1234");
		testuserdto.setCreatedate(new Date());
		
	}

	@Test
	public void addUserSuccessfulTest() {

		when(userRepository.save(testuserentity)).thenReturn(testuserentity);
		
		boolean isUserSaved = userService.addUser(testuserdto);

		assertEquals(isUserSaved, true);

	}

	@Test
	public void existsByemailidTest() {

		when(userRepository.existsByemailid("anurag@gmail.com")).thenReturn(true);

		when(userRepository.existsByemailid("vishal@gmail.com")).thenReturn(false);

		boolean isExists1 = userService.existsByemailid("anurag@gmail.com");

		boolean isExists2 = userService.existsByemailid("vishal@gmail.com");

		assertEquals(isExists1, true);

		assertEquals(isExists2, false);
	}

	@Test
	public void findByemailidTest() {

		when(userRepository.findByemailid("anurag@gmail.com")).thenReturn(testuserentity);
		
		UserEntity testUserEntity2 = userService.findByemailid("anurag@gmail.com");
		
		assertNotNull(testUserEntity2);
		
		assertEquals("Edward", testUserEntity2.getFirstname());
		
	}
	
	@Test
	public void findUserByUserIdTest() {
		
		when(userRepository.findByuserid(3)).thenReturn(testuserentity);
		
		String test_user_email = userService.findUserByUserId(3);
		
		assertNotNull(test_user_email);
		
		assertEquals("edward@gmail.com", test_user_email);
		
	}

}
