package com.qaportal.questions.controller;

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

import com.qaportal.questions.dto.QuestionDTO;
import com.qaportal.questions.entity.QuestionEntity;
import com.qaportal.questions.service.QuestionService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class QuestionControllerTest {
	
	@InjectMocks
	private QuestionController questionController;
	
	@Mock
	private QuestionService questionService;
	
	private QuestionDTO questiondto;
	
	private QuestionEntity questionentity;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(questionController);
		
		questiondto = new QuestionDTO(1, "Java Exception", "Exception that class not found", 1, new Date(),"");
		
		questionentity = new QuestionEntity(1, "Java Exception", "Exception that class not found", 1, new Date());
		
	}
	
	@Test
	public void getAllQuestionsSuccessfulTest() {
		
		List<QuestionDTO> allQuestions = new ArrayList<QuestionDTO>();
		allQuestions.add(questiondto);
	
		when(questionService.getUsernameFromToken("jwt_token")).thenReturn("anurag@gmail.com");
		when(questionService.getAllQuestions()).thenReturn(allQuestions);
		
		ResponseEntity<Object> allQuestionsResponse = questionController.getAllQuestions("jwt_token");
		
		assertNotNull(allQuestionsResponse);
		assertEquals("200 OK", allQuestionsResponse.getStatusCode().toString());
		
	}
	
	@Test
	public void getAllQuestionsUnsuccessfulTest() {
		
		List<QuestionDTO> allQuestions = new ArrayList<QuestionDTO>();
		allQuestions.add(questiondto);
	
		when(questionService.getUsernameFromToken("jwt_token")).thenReturn(null);
		
		ResponseEntity<Object> allQuestionsResponse = questionController.getAllQuestions("jwt_token");
		
		assertNotNull(allQuestionsResponse);
		assertEquals("401 UNAUTHORIZED", allQuestionsResponse.getStatusCode().toString());
		assertEquals("Invalid/Expired Token", allQuestionsResponse.getBody());
		
	}
	
	@Test
	public void postQuestionSuccessfulTest() {
		
		when(questionService.getUsernameFromToken("jwt_token")).thenReturn("anurag@gmail.com");
		
		when(questionService.addQuestion(questiondto, "anurag@gmail.com")).thenReturn(questionentity);
		
		ResponseEntity<Object> postedQuestionResponse = questionController.postQuestion("jwt_token", questiondto);
		
		assertNotNull(postedQuestionResponse);
		assertEquals("201 CREATED", postedQuestionResponse.getStatusCode().toString());
		
	}

	@Test
	public void postQuestionUnsuccessfulTest() {
		
		when(questionService.getUsernameFromToken("jwt_token")).thenReturn(null);
		
		ResponseEntity<Object> postedQuestionResponse = questionController.postQuestion("jwt_token", questiondto);
		
		assertNotNull(postedQuestionResponse);
		assertEquals("401 UNAUTHORIZED", postedQuestionResponse.getStatusCode().toString());
		assertEquals("Invalid/Expired Token", postedQuestionResponse.getBody());
		
	}
	
	@Test
	public void checkIfQuestionExists() {
		
		when(questionService.existsByQuestionId(1)).thenReturn(true);
		
		boolean questionExists = questionController.checkIfQuestionExists(1);
		
		assertEquals(true, questionExists);
		
	}
}
