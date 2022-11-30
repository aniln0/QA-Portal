package com.qaportal.user.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ExceptionResponseStructureTest {
	
	ExceptionResponseStructure ex;
	
	@Test
	public void exceptionResponseStructureTest1() {
		
		ex = new ExceptionResponseStructure(new Date(), "message", "details");
		
		assertEquals(new Date().toString(), ex.getTimestamp().toString());
		assertEquals("message", ex.getMessage());
		assertEquals("details", ex.getDetails());
		
	}

}
