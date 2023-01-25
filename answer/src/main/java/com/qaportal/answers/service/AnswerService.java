package com.qaportal.answers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaportal.answers.dto.AnswerDTO;
import com.qaportal.answers.entity.AnswerEntity;
import com.qaportal.answers.exception.AnswersNotFoundException;
import com.qaportal.answers.feign.QuestionProxy;
import com.qaportal.answers.feign.UserAuthProxy;
import com.qaportal.answers.repository.AnswersRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswersRepository answersRepository;

	@Autowired
	private UserAuthProxy userAuthProxy;

	@Autowired
	private QuestionProxy questionProxy;

	@Autowired
	private ModelMapper modelMapper;

	public List<AnswerDTO> getAllAnswers() {

		// converting list of answerEntity to answerDTO
		List<AnswerDTO> allAnswers = answersRepository.findAll().stream()
				.map(answerEntity -> modelMapper.map(answerEntity, AnswerDTO.class)).collect(Collectors.toList());
		
		return allAnswers;
	}

	public String getUsernameFromToken(String headertoken) {

		try {
			String username = userAuthProxy.getTokenUsername(headertoken);
			return username;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean createAnswer(AnswerDTO answerDTO, String username) {
		int userid = userAuthProxy.getUserId(username);
		answerDTO.setUserid(userid);
		int questionid = answerDTO.getQuestionid();
		if (questionProxy.checkIfQuestionExists(questionid)) {
			// converting dto to entity
			AnswerEntity answerEntity = modelMapper.map(answerDTO, AnswerEntity.class);
			answersRepository.save(answerEntity);
			return true;
		} else {
			return false;
		}
	}

	public List<AnswerDTO> getAnswersByquestionId(int questionid) {

		// converting list of answerEntity to answerDTO
		List<AnswerDTO> answersDTO = answersRepository.findbyQuestionid(questionid).stream()
				.map(answerEntity -> modelMapper.map(answerEntity, AnswerDTO.class)).collect(Collectors.toList());

		if (answersDTO.size() == 0) {
			throw new AnswersNotFoundException(questionid);
		}
		
		for(AnswerDTO answer : answersDTO) {
			String usernameFromUserId = userAuthProxy.getUsernameFromUserId(answer.getUserid());
			answer.setUsername(usernameFromUserId);
		}
		
		return answersDTO;

	}

}
