package com.nhommot.thitracnghiem.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhommot.thitracnghiem.dto.CreateUserDto;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;



@Controller()
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student findStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username);
		return student;
	}
	
	@PostMapping("/create-user")
	public String registerStudent(@ModelAttribute CreateUserDto form, Model model, HttpSession session) throws Exception {
		String usernameSession = (String) session.getAttribute("username");
	    String msv = (String) session.getAttribute("msv");
	    if(msv == null) {
	    	if(usernameSession != null) {
	    		// Check đây là admin
	    		  String username = form.getUsername();
	    	        String password = form.getPassword();
	    	        String fullname = form.getFullname();
	    	        String dobString = form.getDobString();
	    	        String classname = form.getClassname();
	    	        String student_id = form.getStudentId().trim().toUpperCase();
	    	        
	    	        Date dob = null;
	    	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	        try {
	    	            dob = formatter.parse(dobString);
	    	        } catch (ParseException e) {
	    	            e.printStackTrace();
	    	            throw new Exception("Invalid date format");
	    	        }
	    	        
	    	        if (findStudentByUsername(username) != null) {
	    	            model.addAttribute("errorMessage", "Tài khoản đã tồn tại");
	    	            return "dashboard/registerUserByAdmin";
	    	        }
	    	        Student student = new Student();
	    	        student.setUsername(username);
	    	        student.setPassword(password);
	    	        student.setFullname(fullname);
	    	        student.setDob(dob);
	    	        student.setClassname(classname);
	    	        student.setStudentId(student_id);
	    		    studentRepository.save(student);
	    		    
	    		    model.addAttribute("successMessage", "Tạo người dùng thành công");
	    		    return "redirect:/dashboard"; 
	    	}
	    	else {
	    		 return "redirect:/404"; 
	    	}	
	    }
	    else {
	    	return "redirect:/404"; 
	    }
      
	}
}

