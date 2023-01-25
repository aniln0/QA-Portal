package com.qaportal.questions.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomizedResponseEntityExceptionHandlerTest {
	
	ExceptionResponseStructure exceptionResponse;
	
	Exception ex;
	
	WebRequest webrequest;
	
	@InjectMocks
	CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler;
	
	MockHttpServletRequest servletRequest = new MockHttpServletRequest();
	
	@BeforeEach
	void setUp() throws Exception{ 
		
		MockitoAnnotations.initMocks(customizedResponseEntityExceptionHandler);
		
		exceptionResponse = new ExceptionResponseStructure(new Date(), "Exception Message", "Exception Description");
		
		ex = new Exception();
		
		servletRequest.setServerName("www.example.com");
	    servletRequest.setRequestURI("/v1/someuri");
	    servletRequest.addParameter("brand1", "value1");
	    servletRequest.addParameter("brand2", "value2");
	    webrequest = new ServletWebRequest(servletRequest);
		
	}
	
	@Test
	public void handleAllExceptionsTest() throws Exception {
		
		ResponseEntity<Object> handleAllExceptionsResponse = customizedResponseEntityExceptionHandler.handleAllExceptions(ex, webrequest);
		
		assertEquals("500 INTERNAL_SERVER_ERROR", handleAllExceptionsResponse.getStatusCode().toString());
		
	}

	@Test
	public void constraintViolationExceptionTest() throws Exception {
		
		ResponseEntity<Object> constraintViolationExceptionResponse = customizedResponseEntityExceptionHandler.constraintViolationException(ex, webrequest);
		
		assertEquals("400 BAD_REQUEST", constraintViolationExceptionResponse.getStatusCode().toString());
		
	}
	
	@Test
	public void dataIntegrityViolationExceptionTest() throws Exception {
		
		ResponseEntity<Object> dataIntegrityViolationExceptionResponse = customizedResponseEntityExceptionHandler.dataIntegrityViolationException(ex, webrequest);
		
		assertEquals("400 BAD_REQUEST", dataIntegrityViolationExceptionResponse.getStatusCode().toString());
		
	}
	
	@Test
	public void missingRequestHeaderExceptionTest() throws Exception {
		
		ResponseEntity<Object> missingRequestHeaderExceptionResponse = customizedResponseEntityExceptionHandler.missingRequestHeaderException(ex, webrequest);
		
		assertEquals("401 UNAUTHORIZED", missingRequestHeaderExceptionResponse.getStatusCode().toString());
		
	}
}
