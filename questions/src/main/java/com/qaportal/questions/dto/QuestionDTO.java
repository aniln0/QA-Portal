package com.qaportal.questions.dto;

import java.util.Date;

public class QuestionDTO {
	
	private int questionid;
	
	private String title;
	
	private String description;
	
	private int userid;
	
	private Date createdate;
	
	private String username;
	
	public QuestionDTO() {
		super();
	}

	public QuestionDTO(int questionid, String title, String description, int userid, Date createdate, String username) {
		super();
		this.questionid = questionid;
		this.title = title;
		this.description = description;
		this.userid = userid;
		this.createdate = createdate;
		this.username = username;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
