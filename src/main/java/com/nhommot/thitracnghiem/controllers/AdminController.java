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

import com.nhommot.thitracnghiem.dto.CreateAdminDto;
import com.nhommot.thitracnghiem.dto.CreateUserDto;
import com.nhommot.thitracnghiem.dto.LoginAdminDto;
import com.nhommot.thitracnghiem.models.Admin;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.AdminRepository;
import com.nhommot.thitracnghiem.repository.StudentRepository;



@Controller()
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin findAdminByUsername(String username) throws Exception {
		Admin admin = adminRepository.findByUsername(username);
		return admin;
	}
	
	@PostMapping("/create")
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
	
	@PostMapping("/login")
	public String loginAdmin(@ModelAttribute LoginAdminDto form, Model model) throws Exception {
		String username = form.getUsername();
        String password = form.getPassword();
        Admin admin = findAdminByUsername(username);
        if (admin != null) {
            if(admin.getPassword().equals(password.trim())) {
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
	
	@Autowired
	private StudentRepository studentRepository;
	
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
}
