package com.nhommot.thitracnghiem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhommot.thitracnghiem.models.Exam;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.ExamRepository;
import com.nhommot.thitracnghiem.repository.StudentRepository;


@Controller()
@RequestMapping("/")
public class AppController {
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	
	@GetMapping("homepage")
	public String getHomePage(Model model) {
		List<Exam> exams = examRepo.findAll();
		List<Student> students = studentRepo.findAll();
		model.addAttribute("students", students);
		model.addAttribute("exams", exams);
		return "homePage/homePage";
	}
	
	@GetMapping("/")
	public String getLoginPageUser() {
		return "login-user/loginUser.html";
	}
	
	@GetMapping("login-admin")
	public String getLoginPageAdmin() {
		return "login/login.html";
	}
	
	@GetMapping("register-user")
	public String getRegisterPageUser() {
		return "login-user/registerUser.html";
	}
	
	@GetMapping("register-admin")
	public String getRegisterPageAdmin() {
		return "login/register.html";
	}
	
	@GetMapping("exam")
	public String getExamPage() {
		return "exam/exam.html";
	}
	
	
	/*
	 * @GetMapping("dashboard/statistic-page") public String getStatisticPage() {
	 * return "dashboard/statistic.html"; }
	 */
	
	@GetMapping("register-user-by-admin")
	public String getRegisterUserByAdminPage() {
		return "dashboard/registerUserByAdmin.html";
	}
	
	@GetMapping("add-exam")
	public String getCreateExamPage() {
		return "dashboard/addExam.html";
	}
	
	

}
