package com.qaportal.questions.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;



public class QuestionEntityTest {
	
    @Test
    void questionEntityTest() {
        QuestionEntity question=new QuestionEntity();
        question.setQuestionid(1);
        question.setTitle("Java Exception");
        question.setDescription("Exception that class not found");
        question.setUserid(1);
        Date cdate=new Date();
        question.setCreatedate(cdate);
        assertEquals(1,question.getQuestionid());
        assertEquals("Java Exception",question.getTitle());
        assertEquals("Exception that class not found",question.getDescription());
        assertEquals(1,question.getUserid());
        assertEquals(cdate,question.getCreatedate());
    }
    @Test
    void questionEntityTest1() {
        Date cdate=new Date();
        QuestionEntity question=new QuestionEntity(1,"Java Exception","Exception that class not found",1,cdate);
        assertEquals(1,question.getQuestionid());
        assertEquals("Java Exception",question.getTitle());
        assertEquals("Exception that class not found",question.getDescription());
        assertEquals(1,question.getUserid());
        assertEquals(cdate,question.getCreatedate());
    }
    
}