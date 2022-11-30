package com.qaportal.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Size(min = 4, max = 15)
	@Pattern(regexp = "^[a-zA-Z ]*$")
	@NotNull
	private String firstname;

	@Size(min = 4, max = 15)
	@Pattern(regexp = "^[a-zA-Z ]*$")
	@NotNull
	private String lastname;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@NotNull
	private String emailid;

	@NotNull
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdate;

	@PrePersist
	private void onCreate() {
		createdate = new Date();
	}

	public UserEntity() {
		super();
	}

	public UserEntity(int userid, @Size(min = 4, max = 15) @Pattern(regexp = "^[a-zA-Z ]*$") @NotNull String firstname,
			@Size(min = 4, max = 15) @NotNull String lastname,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") @NotNull String emailid,
			@NotNull String password,
			Date createdate) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailid = emailid;
		this.password = password;
		this.createdate = createdate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	
}
