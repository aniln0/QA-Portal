package com.qaportal.questions.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.qaportal.questions.dto.QuestionDTO;
import com.qaportal.questions.entity.QuestionEntity;
import com.qaportal.questions.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	Logger log = LoggerFactory.getLogger(QuestionController.class);

	@GetMapping("/getallquestions")
	@Operation(summary = "Get all Questions", description = "All questions")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> getAllQuestions(@RequestHeader(value = "Authorization") String headertoken) {

		String username = questionService.getUsernameFromToken(headertoken);
		if (username == null) {
			return new ResponseEntity<Object>("Invalid/Expired Token", HttpStatus.UNAUTHORIZED);
		} else {
			List<QuestionDTO> allquestions = questionService.getAllQuestions();
			
			return new ResponseEntity<Object>(allquestions, HttpStatus.OK);
		}

	}

	@PostMapping("/postquestion")
	@Operation(summary = "Post a question", description = "Post a question")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> postQuestion(@RequestHeader(value = "Authorization") String headertoken,
			@RequestBody QuestionDTO questionDTO) {

		String username = questionService.getUsernameFromToken(headertoken);
		if (username == null) {
			return new ResponseEntity<Object>("Invalid/Expired Token", HttpStatus.UNAUTHORIZED);
		} else {
			QuestionEntity savedQuestion = questionService.addQuestion(questionDTO, username);
			return ResponseEntity.created(null).build();
		}

	}

	@GetMapping("/questions/{questionid}")	
	public boolean checkIfQuestionExists(@PathVariable int questionid) {
		return questionService.existsByQuestionId(questionid);
	}

}
