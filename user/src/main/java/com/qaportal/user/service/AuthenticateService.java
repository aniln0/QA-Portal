package com.qaportal.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaportal.user.dto.UserLoginDTO;
import com.qaportal.user.jwt.util.JwtUtil;

@Service
public class AuthenticateService {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	public String authenticate(UserLoginDTO userLoginDTO) {

		String login_mail_id = userLoginDTO.getEmailid();
		String login_password = userLoginDTO.getPassword();
		String user_password_in_db = userService.findByemailid(login_mail_id).getPassword();

		if (userService.checkPass(login_password, user_password_in_db)) {
			return jwtUtil.generateToken(userLoginDTO.getEmailid());
		} else {
			return "Invalid Password";
		}
	}

	public String getUsernameFromToken(String token) {

		try {
			String username = jwtUtil.extractUsername(token.substring(7));
			return username;
		} catch (Exception ex) {
			return null;
		}
	}

}
