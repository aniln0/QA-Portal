package com.qaportal.user.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
public class UserDTOTest {
    @Test
    void userDTOTest() {
    	UserDTO user=new UserDTO();
        user.setUserid(10);
        user.setFirstname("Bhavana");
        user.setLastname("Siri");
        user.setEmailid("bhavanas@gmail.com");
        user.setPassword("Bhavana@25");
        Date cdate=new Date();
        user.setCreatedate(cdate);
        assertEquals(10,user.getUserid());
        assertEquals("Bhavana",user.getFirstname());
        assertEquals("Siri",user.getLastname());
        assertEquals("bhavanas@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
        assertEquals(cdate,user.getCreatedate());
    }
    @Test
    void userDTOTest1() {
        Date cdate=new Date();
        UserDTO user=new UserDTO(10,"Bhavana","Siri","sbhavana@gmail.com","Bhavana@25",cdate);
        assertEquals(10,user.getUserid());
        assertEquals("Bhavana",user.getFirstname());
        assertEquals("Siri",user.getLastname());
        assertEquals("sbhavana@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
        assertEquals(cdate,user.getCreatedate());
    }
    
}