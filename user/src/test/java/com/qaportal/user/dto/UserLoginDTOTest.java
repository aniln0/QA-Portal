package com.qaportal.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserLoginDTOTest {
	
	@Test
	public void UserLoginDTOTest1() {
		UserLoginDTO user=new UserLoginDTO();
        user.setEmailid("bhavanas@gmail.com");
        user.setPassword("Bhavana@25");
        assertEquals("bhavanas@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
	}
	
	@Test
	public void UserLoginDTOTest2() {
		UserLoginDTO user=new UserLoginDTO("bhavanas@gmail.com", "Bhavana@25");
        assertEquals("bhavanas@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
	}

}
