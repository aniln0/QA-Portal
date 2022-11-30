package com.qaportal.user.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoRecordFoundExceptionTest {
	
	@Test
	public void NoRecordFoundExceptionTest1() {
		
		NoRecordFoundException noRecordFoundException = new NoRecordFoundException("afdan@gmail.com");
		
		assertEquals("Incorrect email: afdan@gmail.com", noRecordFoundException.getMessage());
		
	}

}
