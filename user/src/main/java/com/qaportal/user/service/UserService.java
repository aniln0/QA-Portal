package com.qaportal.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.qaportal.user.dto.UserDTO;
import com.qaportal.user.entity.UserEntity;
import com.qaportal.user.exception.NoRecordFoundException;
import com.qaportal.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	public Boolean existsByemailid(String emailid) {

		return userRepository.existsByemailid(emailid);

	}

	public boolean addUser(UserDTO user) {

		String user_entered_password = user.getPassword();
		if (user_entered_password.matches(password_regex)) {
			user.setPassword(hashPassword(user_entered_password));
		} else {
			return false;
		}

		// converting userDTO to userEntity
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userRepository.save(userEntity);

		return true;

	}

//	public List<UserDTO> retriveAllUsers() {
//
//		 //converting list of userEntity to userDTO
//		return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).collect(Collectors.toList());
//
//	}

	public UserEntity findByemailid(String emailid) {

		UserEntity user = userRepository.findByemailid(emailid);
		if (user == null)
			throw new NoRecordFoundException(emailid);
		return user;

	}

	public String findUserByUserId(int userid) {

		UserEntity userEntity = userRepository.findByuserid(userid);

		return userEntity.getEmailid();

	}

//	public UserEntity getPasswordByUsername(String emailid) {
//		UserEntity user = userRepository.findByemailid(emailid);
//		if(user == null)
//			throw new NoRecordFoundException(emailid);
//		return user;
//	}

	// Password Encoding AND Checking

	public String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	public boolean checkPass(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	
	

}
