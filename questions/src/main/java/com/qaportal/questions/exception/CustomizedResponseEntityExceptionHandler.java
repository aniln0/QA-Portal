package com.qaportal.questions.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		ExceptionResponseStructure exceptionResponse = new ExceptionResponseStructure(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> constraintViolationException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponseStructure exceptionResponse = new ExceptionResponseStructure(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse.getMessage(), HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> dataIntegrityViolationException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponseStructure exceptionResponse = new ExceptionResponseStructure(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public final ResponseEntity<Object> missingRequestHeaderException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponseStructure exceptionResponse = new ExceptionResponseStructure(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse.getMessage(), HttpStatus.UNAUTHORIZED);

	}


}
