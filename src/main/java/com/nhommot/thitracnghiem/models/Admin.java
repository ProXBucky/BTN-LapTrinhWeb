package com.nhommot.thitracnghiem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idadmin")
    private Long idadmin;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "password")
    private String password;

	public Long getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(Long idadmin) {
		this.idadmin = idadmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}