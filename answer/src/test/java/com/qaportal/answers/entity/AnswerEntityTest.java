package com.qaportal.answers.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;



public class AnswerEntityTest {
    @Test
    void answerEntityTest() {
        AnswerEntity answer=new AnswerEntity();
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
    void answerEntityTest1() {
        Date cdate=new Date();
        AnswerEntity answer=new AnswerEntity(1,"You can try by checking name of class",1,1,cdate);
        assertEquals(1,answer.getAnswerid());
        assertEquals("You can try by checking name of class",answer.getAnswer());
        assertEquals(1,answer.getQuestionid());
        assertEquals(1,answer.getUserid());
        assertEquals(cdate,answer.getCreatedate());
    }
//    @Test
//    void answerEntityTest2() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can try by checking name of class");
//        answer.setQuestionid(1);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        assertNotEquals(answer,null);
//    }
//    @Test
//    void answerEntityTest3() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can try by checking name of class");
//        answer.setQuestionid(1);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        assertEquals(answer,answer);
//    }
//    @Test
//    void answerEntityTest4() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can try by checking name of class");
//        answer.setQuestionid(1);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        AnswerEntity answer1=new AnswerEntity();
//        answer1.setAnswerid(1);
//        answer1.setAnswer("You can do checking name of class");
//        answer1.setQuestionid(1);
//        answer1.setUserid(1);
//        Date cdate1=new Date();
//        answer1.setCreatedate(cdate1);
//        assertNotEquals(answer,answer1);
//    }
//    @Test
//    void answerEntityTest5() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can try by checking name of class");
//        answer.setQuestionid(1);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        AnswerEntity answer1=new AnswerEntity();
//        answer1.setAnswerid(1);
//        answer1.setAnswer("You can do checking name of class");
//        answer1.setQuestionid(2);
//        answer1.setUserid(1);
//        Date cdate1=new Date();
//        answer1.setCreatedate(cdate1);
//        assertNotEquals(answer,answer1);
//    }
//    @Test
//    void answerEntityTest6() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer(null);
//        answer.setQuestionid(1);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        AnswerEntity answer1=new AnswerEntity();
//        answer1.setAnswerid(1);
//        answer1.setAnswer("You can do checking name of class");
//        answer1.setQuestionid(1);
//        answer1.setUserid(1);
//        Date cdate1=new Date();
//        answer1.setCreatedate(cdate1);
//        assertNotEquals(answer,answer1);
//    }
//    @Test
//    void answerEntityTest7() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can do checking name of class");
//        answer.setQuestionid(2);
//        answer.setUserid(1);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        AnswerEntity answer1=new AnswerEntity();
//        answer1.setAnswerid(1);
//        answer1.setAnswer("You can do checking name of class");
//        answer1.setQuestionid(1);
//        answer1.setUserid(1);
//        Date cdate1=new Date();
//        answer1.setCreatedate(cdate1);
//        assertNotEquals(answer,answer1);
//    }
//    @Test
//    void answerEntityTest8() {
//        AnswerEntity answer=new AnswerEntity();
//        answer.setAnswerid(1);
//        answer.setAnswer("You can do checking name of class");
//        answer.setQuestionid(1);
//        answer.setUserid(3);
//        Date cdate=new Date();
//        answer.setCreatedate(cdate);
//        AnswerEntity answer1=new AnswerEntity();
//        answer1.setAnswerid(1);
//        answer1.setAnswer("You can do checking name of class");
//        answer1.setQuestionid(1);
//        answer1.setUserid(1);
//        Date cdate1=new Date();
//        answer1.setCreatedate(cdate1);
//        assertNotEquals(answer,answer1);
//    }
}