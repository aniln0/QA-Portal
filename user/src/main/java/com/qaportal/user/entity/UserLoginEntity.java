package com.qaportal.user.entity;

public class UserLoginEntity {

	private String emailid;

	private String password;

	public UserLoginEntity() {
		super();
	}

	public UserLoginEntity(String emailid, String password) {
		super();
		this.emailid = emailid;
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
