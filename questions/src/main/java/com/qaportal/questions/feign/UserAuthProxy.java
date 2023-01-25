package com.qaportal.questions.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user")
public interface UserAuthProxy {
	
	@GetMapping("/tokenusername")
	public String getTokenUsername(@RequestHeader(value = "Authorization") String token);
	
	@GetMapping("/getuserid/{username}")
	public int getUserId(@PathVariable String username);
	
	@GetMapping("/getUsername/{userid}") 
	public String getUsernameFromUserId(@PathVariable int userid);

}
