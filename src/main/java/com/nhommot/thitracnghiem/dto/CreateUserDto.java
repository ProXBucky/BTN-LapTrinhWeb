package com.nhommot.thitracnghiem.dto;


import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

public class CreateUserDto {
	@NotEmpty(message = "The username is required")
    private String username;

	@NotEmpty(message = "The fullname is required")
    private String fullname;

	@NotEmpty(message = "The password is required")
    private String password;
	
	@NotEmpty(message = "The dob is required")
    private String dobString;

	@NotEmpty(message = "The classname is required")
    private String classname;

	@NotEmpty(message = "The studentId is required")
    private String studentId;
	
	private Date dob;
	
	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDobString() {
		return dobString;
	}

	public void setDobString(String dobString) {
		this.dobString = dobString;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	
}
