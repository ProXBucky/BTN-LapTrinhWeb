package com.nhommot.thitracnghiem.dto;
import jakarta.validation.constraints.NotEmpty;

public class CreateAdminDto {
	
	@NotEmpty(message = "The username is required")
    private String username;

	@NotEmpty(message = "The fullname is required")
    private String fullname;

	@NotEmpty(message = "The password is required")
    private String password;

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
	
	
}
