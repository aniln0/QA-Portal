package com.qaportal.answers.controller;

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

import com.qaportal.answers.dto.AnswerDTO;
import com.qaportal.answers.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AnswersController {

	@Autowired
	private AnswerService answerService;

	Logger log = LoggerFactory.getLogger(AnswersController.class);

	@GetMapping("/getallanswers")
	@Operation(summary = "Get all users", description = "All users")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> getAllAnswers(@RequestHeader(value = "Authorization") String headertoken) {

		String username = answerService.getUsernameFromToken(headertoken);
		if (username == null) {
			return new ResponseEntity<Object>("Invalid/Expired Token", HttpStatus.UNAUTHORIZED);
		} else {
			List<AnswerDTO> allAnswers = answerService.getAllAnswers();
			return new ResponseEntity<Object>(allAnswers, HttpStatus.OK);
		}

	}

	@PostMapping("/postanswer")
	@Operation(summary = "Get all users", description = "All users")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> postAnswer(@RequestHeader(value = "Authorization") String headertoken,
			@RequestBody AnswerDTO answerDTO) {

		String username = answerService.getUsernameFromToken(headertoken);
		if (username == null) {
			return new ResponseEntity<Object>("Invalid/Expired Token", HttpStatus.UNAUTHORIZED);
		} else {
			boolean isSuccessful = answerService.createAnswer(answerDTO, username);
			if (isSuccessful)
				return new ResponseEntity<Object>("Answer Posted", HttpStatus.CREATED);
			return new ResponseEntity<Object>("Question Id does not exist", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/getanswers/{questionid}")
	public ResponseEntity<Object> getAnswers(@RequestHeader(value = "Authorization") String headertoken, @PathVariable int questionid) {
		
		List<AnswerDTO> answers = answerService.getAnswersByquestionId(questionid);
		
		return new ResponseEntity<Object>(answers, HttpStatus.ACCEPTED);
		
	}

}
