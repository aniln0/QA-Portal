package com.qaportal.answers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.qaportal.answers.dto.AnswerDTO;
import com.qaportal.answers.service.AnswerService;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnswerControllerTest {
	
	@Mock
	private AnswerService answerService;
	
	@InjectMocks
	private AnswersController answerController;
	
	private AnswerDTO answerDTO;
	
	@BeforeEach
    void setUp() throws Exception{
		
        MockitoAnnotations.initMocks(answerController);
        
        answerDTO = new AnswerDTO(1, "Try adding dependencies", 1, 1, new Date(),"");
        
        
    }
	
	@Test
	public void getAllAnswersSuccessfulTest() {
		
	   	List<AnswerDTO> allAnswers = new ArrayList<AnswerDTO>();
	   	allAnswers.add(answerDTO);
		
		when(answerService.getUsernameFromToken("jwt_token")).thenReturn("anurag@gmail.com");
		when(answerService.getAllAnswers()).thenReturn(allAnswers);
		
		ResponseEntity<Object> allAnswersResponse = answerController.getAllAnswers("jwt_token");
		
		assertNotNull(allAnswersResponse);
		assertEquals("200 OK", allAnswersResponse.getStatusCode().toString());
		
	}
	
	@Test
	public void getAllAnswersUnsuccessfulTest() {
		
	   	List<AnswerDTO> allAnswers = new ArrayList<AnswerDTO>();
	   	allAnswers.add(answerDTO);
		
		when(answerService.getUsernameFromToken("jwt_token")).thenReturn(null);
		
		ResponseEntity<Object> allAnswersResponse = answerController.getAllAnswers("jwt_token");
		
		assertNotNull(allAnswersResponse);
		assertEquals("401 UNAUTHORIZED", allAnswersResponse.getStatusCode().toString());
		assertEquals("Invalid/Expired Token", allAnswersResponse.getBody());
		
	}

	@Test
	public void postAnswerSuccessfulTest(){
		
		when(answerService.getUsernameFromToken("jwt_token")).thenReturn("anurag@gmail.com");
		when(answerService.createAnswer(answerDTO, "anurag@gmail.com")).thenReturn(true);
		
		ResponseEntity<Object> postedAnswersResponse = answerController.postAnswer("jwt_token", answerDTO);
		
		assertEquals("201 CREATED", postedAnswersResponse.getStatusCode().toString());
		assertEquals("Answer Posted", postedAnswersResponse.getBody());
	
	}
	
	@Test
	public void postAnswerUnsuccessfulTest(){
	
		when(answerService.getUsernameFromToken("jwt_token")).thenReturn("anurag@gmail.com");
		when(answerService.createAnswer(answerDTO, "anurag@gmail.com")).thenReturn(false);
		
		ResponseEntity<Object> postedAnswersResponse = answerController.postAnswer("jwt_token", answerDTO);
		
		assertEquals("400 BAD_REQUEST", postedAnswersResponse.getStatusCode().toString());
		assertEquals("Question Id does not exist", postedAnswersResponse.getBody());
	
	}
	
	@Test
	public void postAnswerUnsuccessfulDueToInvalidTokenTest(){
    	
		when(answerService.getUsernameFromToken("jwt_token")).thenReturn(null);
		
		ResponseEntity<Object> postedAnswersResponse = answerController.postAnswer("jwt_token", answerDTO);
		
		assertEquals("401 UNAUTHORIZED", postedAnswersResponse.getStatusCode().toString());
		assertEquals("Invalid/Expired Token", postedAnswersResponse.getBody());
	
	}
	
	@Test
	public void getAnswers() {
	   	
	   	List<AnswerDTO> allAnswers = new ArrayList<AnswerDTO>();
	   	allAnswers.add(answerDTO);
		
		when(answerService.getAnswersByquestionId(1)).thenReturn(allAnswers);
		
		ResponseEntity<Object> answersByQuestionIdResponse = answerController.getAnswers("jwt_token", 1);
		
		assertEquals("202 ACCEPTED", answersByQuestionIdResponse.getStatusCode().toString());
		
	}
	
}
