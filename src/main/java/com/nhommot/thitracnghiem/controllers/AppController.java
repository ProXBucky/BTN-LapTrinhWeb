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

import jakarta.servlet.http.HttpSession;


@Controller()
@RequestMapping("/")
public class AppController {
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student findStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username);
		return student;
	}
	
	
	@GetMapping("homepage")
	public String getHomePage(Model model, HttpSession session) {
	    try {
	    	String username = (String) session.getAttribute("username");
	        String fullname = (String) session.getAttribute("fullname");
	        String msv = (String) session.getAttribute("msv");
			if(msv != null) {
				if(username != null) {
					// Check đây là admin
				            Student student = findStudentByUsername(username);
				            List<Exam> exams = examRepo.findAll();
				            model.addAttribute("student", student.getStudentId());
				            model.addAttribute("exams", exams);
				            model.addAttribute("fullname", fullname);
				            return "homePage/homePage";
				
				} 
				else {
				     return "redirect:/login";    	 
				}
			}
			else {
					 return "redirect:/404"; 
			}	
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "errorPage";
	    }
	}

	
	@GetMapping("/")
	public String getLoginPageUser(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				return "redirect:/homepage";
			}else {
				return "login-user/loginUser.html";				
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	@GetMapping("login")
	public String getLoginPageUser1(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				return "redirect:/homepage";
			}else {
				return "login-user/loginUser.html";				
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}	}
	
	@GetMapping("login-admin")
	public String getLoginPageAdmin(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				return "redirect:/homepage";
			}else {
				return "login/login";				
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	@GetMapping("register-user")
	public String getRegisterPageUser(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				return "redirect:/homepage";
			}else {
				return "login-user/registerUser";				
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	@GetMapping("/register-admin")
	public String getRegisterPageAdmin(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			if(username != null) {
				return "redirect:/homepage";
			}else {
				return "login/register";				
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	@GetMapping("/404")
	public String getExamPage() {
		  return "homePage/404.html";
	}

}


