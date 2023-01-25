package com.qaportal.answers.exception;

public class AnswersNotFoundException extends RuntimeException {
	
	public AnswersNotFoundException(int id) {
		super("Answers not found for this question id: " + id);
	}

}
