package com.qaportal.answers.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity(name = "answer")
public class AnswerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerid;
	
	@NotNull
	@Size(min = 5, max = 300)
	private String answer;
	
	@NotNull
	private int questionid;
	
	@NotNull
	private int userid;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date createdate;

	@PrePersist
	private void onCreate() {
		createdate = new Date();
	}
	
	public AnswerEntity() {
		super();
	}
	
	public AnswerEntity(int answerid, @Size(min = 5, max = 300) String answer, int questionid, int userid,
			Date createdate) {
		super();
		this.answerid = answerid;
		this.answer = answer;
		this.questionid = questionid;
		this.userid = userid;
		this.createdate = createdate;
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
	
	
	
}
