package com.qaportal.answers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "questions")
public interface QuestionProxy {
	
	@GetMapping("/questions/{questionid}")
	public boolean checkIfQuestionExists(@PathVariable int questionid);

}
