package com.qaportal.questions.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "question")
public class QuestionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionid;
	
	@NotNull
	@Size(min = 5, max = 200)
	private String title;
	
	@NotNull
	@Size(min = 10, max = 300)
	private String description;
	
	@NotNull
	private int userid;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date createdate;

	@PrePersist
	private void onCreate() {
		createdate = new Date();
	}

	public QuestionEntity() {
		super();
	}

	public QuestionEntity(int questionid, @NotNull @Size(max = 200) String title,
			@NotNull @Size(max = 300) String description,
		    int userid,
			@NotNull Date createdate) {
		super();
		this.questionid = questionid;
		this.title = title;
		this.description = description;
		this.userid = userid;
		this.createdate = createdate;
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

	
}
