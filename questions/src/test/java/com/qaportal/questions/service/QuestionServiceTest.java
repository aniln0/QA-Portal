package com.qaportal.questions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.qaportal.questions.dto.QuestionDTO;
import com.qaportal.questions.entity.QuestionEntity;
import com.qaportal.questions.feign.UserAuthProxy;
import com.qaportal.questions.repository.QuestionRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class QuestionServiceTest {
	
    @InjectMocks
    private QuestionService questionService;
    
    @Mock
    private QuestionRepository questionRepository;
    
    @Mock
	private ModelMapper modelMapper;
    
    @Mock
    private UserAuthProxy userAuthProxy;
    
    private QuestionEntity question1, question2;
    
    private QuestionDTO questiondto;
    
    @BeforeEach
    void setUp() throws Exception{
    	
        MockitoAnnotations.initMocks(questionService);
        
        question1 = new QuestionEntity();
        question1.setQuestionid(1);
        question1.setTitle("Java Exception");
        question1.setDescription("Exception that class not found");
        question1.setUserid(1);
        
        question2 = new QuestionEntity();
        question2.setQuestionid(1);
        question2.setTitle("Java Exception");
        question2.setDescription("Exception that class not found");
        question2.setUserid(1);
        
        questiondto = new QuestionDTO();
    	questiondto.setQuestionid(1);
    	questiondto.setTitle("Java Exception");
    	questiondto.setDescription("Exception that class not found");
    	questiondto.setUserid(1);
        
    }
    
//    @Test
//    public void getAllQuestionsTest() {
//    	
//        List<QuestionEntity> questionEntities = new ArrayList<>();
//        questionEntities.add(question1);
//        questionEntities.add(question2);
//        
//        List<QuestionDTO> questionDTO = new ArrayList<QuestionDTO>();
//        questionDTO.add(questiondto);
//        
//        when(questionRepository.findAll()).thenReturn(questionEntities);
//        when(userAuthProxy.getUsernameFromUserId(questionDTO.get(0).getUserid())).thenReturn("user1@gmail.com");
//        
//        List<QuestionDTO> allQuestions = questionService.getAllQuestions();
//        
//        
//        assertNotNull(allQuestions);
//        assertEquals(allQuestions.size(), 2);
//        
//    }
    
    @Test
    public void addQuestionTest() {
    	
    	when(userAuthProxy.getUserId("anurag@gmail.com")).thenReturn(7);
    	when(questionRepository.save(question1)).thenReturn(question1);
    	
    	questionService.addQuestion(questiondto, "anurag@gmail.com");
    	
    }
    
    @Test
    public void existsByQuestionIdTest() {
        
        when(questionRepository.existsById(1)).thenReturn(true);
        
        boolean existsByQuestionId = questionService.existsByQuestionId(1);
        
        assertTrue(existsByQuestionId);
    }
    
    @Test
    public void getUsernameFromTokenTest() {
    	
    	when(userAuthProxy.getTokenUsername("jwt_token")).thenReturn("anurag@gmail.com");
    	
    	String usernameFromToken = questionService.getUsernameFromToken("jwt_token");
    	
    	assertNotNull(usernameFromToken);
    	assertEquals("anurag", usernameFromToken.substring(0, 6));
    	
    }
    
}
