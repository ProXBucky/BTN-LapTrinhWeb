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
import com.nhommot.thitracnghiem.dto.LoginAdminDto;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.StudentRepository;

@Controller()
@RequestMapping("/user")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	public Student findStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username);
		return student;
	}
	
	@PostMapping("/create")
    public String registerStudent(@ModelAttribute CreateUserDto form, Model model) throws Exception {
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
            return "login-user/registerUser";
        }
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setFullname(fullname);
        student.setDob(dob);
        student.setClassname(classname);
        student.setStudentId(student_id);
	    studentRepository.save(student);
	    return "redirect:/login-user";
	}
	
	@PostMapping("/login")
	public String loginAdmin(@ModelAttribute LoginAdminDto form, Model model) throws Exception {
		String username = form.getUsername();
        String password = form.getPassword();
        Student student = findStudentByUsername(username);
        if (student != null) {
            if(student.getPassword().equals(password.trim())) {
            	return "redirect:/homepage";
            }else {
            	 model.addAttribute("errorMessage", "Sai mật khẩu");
                 return "login-user/loginUser";
            }
        }else {
            model.addAttribute("errorMessage", "Tài khoản không tồn tại, hãy đăng ký tài khoản");
            return "login-user/loginUser";
        }
	}

}
