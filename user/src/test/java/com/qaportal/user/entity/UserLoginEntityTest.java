package com.qaportal.user.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserLoginEntityTest {
	
	@Test
	public void userLoginEntityTest1() {
		UserLoginEntity user=new UserLoginEntity();
        user.setEmailid("bhavanas@gmail.com");
        user.setPassword("Bhavana@25");
        assertEquals("bhavanas@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
	}
	
	@Test
	public void userLoginEntityTest2() {
		UserLoginEntity user=new UserLoginEntity("bhavanas@gmail.com", "Bhavana@25");
        assertEquals("bhavanas@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
	}

}
