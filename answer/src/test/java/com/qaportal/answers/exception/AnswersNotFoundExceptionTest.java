package com.qaportal.answers.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnswersNotFoundExceptionTest {
	
	@Test
	public void answersNotFoundExceptionTest1() {
		
		AnswersNotFoundException answersNotFoundException = new AnswersNotFoundException(0);
		
		assertEquals("Answers not found for this question id: 0", answersNotFoundException.getMessage());
	}
	

}
