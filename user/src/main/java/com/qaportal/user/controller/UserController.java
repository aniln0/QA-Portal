package com.qaportal.user.controller;

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

import com.qaportal.user.dto.UserDTO;
import com.qaportal.user.dto.UserLoginDTO;
import com.qaportal.user.service.AuthenticateService;
import com.qaportal.user.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticateService authService;

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/registeruser")
	public ResponseEntity<Object> registerUser(@RequestBody UserDTO user) {

		if (userService.existsByemailid(user.getEmailid()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered!");

		boolean isSuccessful = userService.addUser(user);

		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password");
	}	

//	@GetMapping("/getallusers")
//	@Operation(summary = "Get all users", description = "All users")
//	@SecurityRequirement(name = "Bearer Authentication")
//	public ResponseEntity<Object> getAllUsers(@RequestHeader(value = "Authorization") String token) {
//
//		String username = authService.getUsernameFromToken(token);
//		if (username == null)
//			return new ResponseEntity<Object>("Invalid/Expired Token", HttpStatus.BAD_REQUEST);
//		List<UserDTO> users = userService.retriveAllUsers();
//		return new ResponseEntity<Object>(users, HttpStatus.OK);
//
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<Object> generateToken(@RequestBody UserLoginDTO userLoginDTO) {

		String response = authService.authenticate(userLoginDTO);
		if (response.equals("Invalid Password"))
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping("/tokenusername")
	
	public String getTokenUsername(@RequestHeader(value = "Authorization") String token) {

		String username = authService.getUsernameFromToken(token);
		return username;

	}

	@GetMapping("/getuserid/{username}")
	
	public int getUserId(@PathVariable String username) {
		
		return userService.findByemailid(username).getUserid();
	}
	
	@GetMapping("/getUsername/{userid}") 
	public String getUsernameFromUserId(@PathVariable int userid) {
//		String usernametoken = authService.getUsernameFromToken(token);
		return userService.findUserByUserId(userid);
	}

}