package com.qaportal.user.exception;

public class NoRecordFoundException extends RuntimeException {
	
	public NoRecordFoundException(String message) {
		super("Incorrect email: " + message);
	}

}
