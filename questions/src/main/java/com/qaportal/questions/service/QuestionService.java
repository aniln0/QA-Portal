package com.qaportal.questions.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaportal.questions.dto.QuestionDTO;
import com.qaportal.questions.entity.QuestionEntity;
import com.qaportal.questions.feign.UserAuthProxy;
import com.qaportal.questions.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserAuthProxy userAuthProxy;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<QuestionDTO> getAllQuestions() {
		
		//converting list of questionEntity to questionDTO
		List<QuestionDTO> allQuestions = questionRepository.findAll().stream().map(questionEntity -> modelMapper.map(questionEntity, QuestionDTO.class)).collect(Collectors.toList());
		for(QuestionDTO question : allQuestions) {
			String usernameFromUserId = userAuthProxy.getUsernameFromUserId(question.getUserid());
			question.setUsername(usernameFromUserId);
		}
		return allQuestions;
	}

	public QuestionEntity addQuestion(QuestionDTO questionDTO, String username) {
		int userid = userAuthProxy.getUserId(username);
		questionDTO.setUserid(userid);
		//converting questionDTO to questionEntity
		QuestionEntity questionEntity = modelMapper.map(questionDTO, QuestionEntity.class);
		QuestionEntity savedQuestion = questionRepository.save(questionEntity);
		return savedQuestion;
	}

	public boolean existsByQuestionId(int questionid) {
		return questionRepository.existsById(questionid);
	}

	public String getUsernameFromToken(String headertoken) {
		
		try {
			String username = userAuthProxy.getTokenUsername(headertoken);
			return username;
		} catch (Exception e) {
			return null;
		}
		
	}

}
