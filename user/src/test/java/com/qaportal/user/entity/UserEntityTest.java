package com.qaportal.user.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
public class UserEntityTest {
    @Test
    void userEntityTest() {
        UserEntity user=new UserEntity();
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
    void userEntityTest1() {
        Date cdate=new Date();
        UserEntity user=new UserEntity(10,"Bhavana","Siri","sbhavana@gmail.com","Bhavana@25",cdate);
        assertEquals(10,user.getUserid());
        assertEquals("Bhavana",user.getFirstname());
        assertEquals("Siri",user.getLastname());
        assertEquals("sbhavana@gmail.com",user.getEmailid());
        assertEquals("Bhavana@25",user.getPassword());
        assertEquals(cdate,user.getCreatedate());
    }
    
}