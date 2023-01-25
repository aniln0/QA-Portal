package com.qaportal.answers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.modelmapper.ModelMapper;

import com.qaportal.answers.dto.AnswerDTO;
import com.qaportal.answers.entity.AnswerEntity;
import com.qaportal.answers.feign.QuestionProxy;
import com.qaportal.answers.feign.UserAuthProxy;
import com.qaportal.answers.repository.AnswersRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnswerServiceTest {
	
    @InjectMocks
    AnswerService answerService;
    
    @Mock
    private AnswersRepository answersRepository;
    
    @Mock
	private ModelMapper modelMapper;
    
    @Mock
    private UserAuthProxy userAuthProxy;
    
    @Mock
    private QuestionProxy questionProxy;
    
    private AnswerEntity answerEntity;
    
    private AnswerDTO answerDTO;
    
    @BeforeEach
    void setUp() throws Exception{
    	
        MockitoAnnotations.initMocks(answerService);
        
        answerEntity = new AnswerEntity();
        answerEntity.setAnswerid(1);
        answerEntity.setAnswer("Try adding dependencies");
        answerEntity.setQuestionid(1);
        answerEntity.setUserid(1);
        answerEntity.setCreatedate(new Date());
        
        answerDTO = new AnswerDTO();
    	answerDTO.setAnswerid(1);
    	answerDTO.setAnswer("Try adding dependencies");
    	answerDTO.setQuestionid(1);
    	answerDTO.setUserid(1);
    	answerDTO.setCreatedate(new Date());
    	answerDTO.setUsername("user");
        
    }

    @Test
    void getAllAnswerTest() {
    	
        List<AnswerEntity> answerEntities = new ArrayList<>();
        answerEntities.add(answerEntity);
        
        when(answersRepository.findAll()).thenReturn(answerEntities);
        
        List<AnswerDTO> allAnswers = answerService.getAllAnswers();
        
        assertEquals(allAnswers.size(), 1);
        assertNotNull(allAnswers);
    }
    
    @Test
    public void getUsernameFromTokenTest() {
    	
    	when(userAuthProxy.getTokenUsername("jwt_token")).thenReturn("anurag@gmail.com");
    	
    	String usernameFromToken = answerService.getUsernameFromToken("jwt_token");
    	
    	assertNotNull(usernameFromToken);
    	assertEquals("anurag", usernameFromToken.substring(0, 6));
    	
    }
    
    @Test
    public void createAnswerSuccessfulTest() {
    	
    	when(userAuthProxy.getUserId("anurag@gmail.com")).thenReturn(7);
    	when(questionProxy.checkIfQuestionExists(1)).thenReturn(true);
    	when(answersRepository.save(answerEntity)).thenReturn(answerEntity);
    	
    	boolean isAnswerCreated = answerService.createAnswer(answerDTO, "jwt_token");
    	
    	assertTrue(isAnswerCreated);
    }
    
    
    @Test
    public void createAnswerUnsuccessfulTest() {
    	
    	when(userAuthProxy.getUserId("anurag@gmail.com")).thenReturn(7);
    	when(questionProxy.checkIfQuestionExists(1)).thenReturn(false);
    	 	
    	boolean isAnswerCreated = answerService.createAnswer(answerDTO, "jwt_token");
    	
    	assertFalse(isAnswerCreated);
    }
    
    

//    @Test
//    void getAnswersByquestionIdTest() {
//    	 
//        List<AnswerDTO> answerdto = new ArrayList<>();
//        answerdto.add(answerDTO);
//        
//        List<AnswerEntity> answerEntities = new ArrayList<>();
//        answerEntities.add(answerEntity);
//        
//        when(answersRepository.findbyQuestionid(1)).thenReturn(answerEntities);
//        
//        when(userAuthProxy.getUsernameFromUserId(answerdto.get(0).getUserid())).thenReturn("username");
//        
//        List<AnswerDTO> answersByquestionId = answerService.getAnswersByquestionId(1);
//        
//        assertNotNull(answersByquestionId);
//        assertEquals(answersByquestionId.size(), 1);
//        
//    }
    
}

