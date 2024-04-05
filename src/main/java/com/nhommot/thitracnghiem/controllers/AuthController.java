package com.nhommot.thitracnghiem.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhommot.thitracnghiem.dto.CreateAdminDto;
import com.nhommot.thitracnghiem.dto.CreateUserDto;
import com.nhommot.thitracnghiem.dto.LoginAdminDto;
import com.nhommot.thitracnghiem.models.Admin;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.AdminRepository;
import com.nhommot.thitracnghiem.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin findAdminByUsername(String username) throws Exception {
		Admin admin = adminRepository.findByUsername(username);
		return admin;
	}
	
	
	public Student findStudentByUsername(String username) {
		Student student = studentRepository.findByUsername(username);
		return student;
	}
	
	@PostMapping("/create-user")
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
	
	@PostMapping("/login-user")
	public String loginUser(@ModelAttribute LoginAdminDto form,  Model model , HttpSession session) throws Exception {
		String username = form.getUsername();
        String password = form.getPassword();
        Student student = findStudentByUsername(username);
        if (student != null) {
            if(student.getPassword().equals(password.trim())) {
            	session.setAttribute("username", username);
            	session.setAttribute("fullname", student.getFullname());
            	session.setAttribute("msv", student.getStudentId());
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
	
	
	
	@PostMapping("/create-admin")
    public String registerAdmin(@ModelAttribute CreateAdminDto form, Model model) throws Exception {
        String username = form.getUsername();
        String password = form.getPassword();
        String fullname = form.getFullname();
        if (findAdminByUsername(username) != null) {
            model.addAttribute("errorMessage", "Admin đã tồn tại");
            return "login/register";
        }
        Admin admin = new Admin();
	    admin.setUsername(username);
	    admin.setPassword(password);
	    admin.setFullName(fullname);
	    adminRepository.save(admin);
	    return "redirect:/login-admin"; 
	}
	
	
	@PostMapping("/login-admin")
	public String loginAdmin(@ModelAttribute LoginAdminDto form,  Model model , HttpSession session) throws Exception {
		String username = form.getUsername();
        String password = form.getPassword();
        Admin admin = findAdminByUsername(username);
        if (admin != null) {
            if(admin.getPassword().equals(password.trim())) {
            	session.setAttribute("username", username);
            	session.setAttribute("fullname", admin.getFullName());
            	return "redirect:/dashboard";
            }else {
            	 model.addAttribute("errorMessage", "Sai mật khẩu");
                 return "login/login";
            }
        }else {
            model.addAttribute("errorMessage", "Tài khoản không tồn tại, hãy đăng ký tài khoản");
            return "login/login";
        }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
	
}
