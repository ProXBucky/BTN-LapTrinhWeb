package com.nhommot.thitracnghiem.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idsinhvien")
    private Long idsinhvien;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password")
    private String password;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "classname")
    private String classname;

    @Column(name = "studentId")
    private String studentId;

	public Long getIdsinhvien() {
		return idsinhvien;
	}

	public void setIdsinhvien(Long idsinhvien) {
		this.idsinhvien = idsinhvien;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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
