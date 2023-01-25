package com.qaportal.answers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qaportal.answers.entity.AnswerEntity;

public interface AnswersRepository extends JpaRepository<AnswerEntity, Integer> {
	
	@Query(value = "select * from answer where questionid = ?1", nativeQuery = true)
	List<AnswerEntity> findbyQuestionid(int questionid);

}
