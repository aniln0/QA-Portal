package com.qaportal.answers.dto;

import java.util.Date;

public class AnswerDTO {

	private int answerid;

	private String answer;

	private int questionid;

	private int userid;

	private Date createdate;
	
	private String username;
	
	public AnswerDTO() {
		super();
	}

	public AnswerDTO(int answerid, String answer, int questionid, int userid, Date createdate, String username) {
		super();
		this.answerid = answerid;
		this.answer = answer;
		this.questionid = questionid;
		this.userid = userid;
		this.createdate = createdate;
		this.username = username;
	}

	public int getAnswerid() {
		return answerid;
	}

	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
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
