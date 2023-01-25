package com.qaportal.answers.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AnswerDTOTest {
	
	@Test
    void answerDTOTest() {
        AnswerDTO answer=new AnswerDTO();
        answer.setAnswerid(1);
        answer.setAnswer("You can try by checking name of class");
        answer.setQuestionid(1);
        answer.setUserid(1);
        Date cdate=new Date();
        answer.setCreatedate(cdate);
        assertEquals(1,answer.getAnswerid());
        assertEquals("You can try by checking name of class",answer.getAnswer());
        assertEquals(1,answer.getQuestionid());
        assertEquals(1,answer.getUserid());
        assertEquals(cdate,answer.getCreatedate());
    }
	
    @Test
    void answerDTOTest1() {
        Date cdate=new Date();
        AnswerDTO answer=new AnswerDTO(1,"You can try by checking name of class",1,1,cdate,"");
        assertEquals(1,answer.getAnswerid());
        assertEquals("You can try by checking name of class",answer.getAnswer());
        assertEquals(1,answer.getQuestionid());
        assertEquals(1,answer.getUserid());
        assertEquals(cdate,answer.getCreatedate());
    }

}
