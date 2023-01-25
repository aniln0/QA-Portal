package com.qaportal.questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qaportal.questions.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

}
