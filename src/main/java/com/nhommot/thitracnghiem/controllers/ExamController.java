package com.nhommot.thitracnghiem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhommot.thitracnghiem.models.Answers;
import com.nhommot.thitracnghiem.models.Exam;
import com.nhommot.thitracnghiem.models.Questions;
import com.nhommot.thitracnghiem.models.Result;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.repository.AnswersRepository;
import com.nhommot.thitracnghiem.repository.ExamRepository;
import com.nhommot.thitracnghiem.repository.QuestionsRepository;
import com.nhommot.thitracnghiem.repository.ResultRepository;
import com.nhommot.thitracnghiem.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/exam")
public class ExamController {
	
	@Autowired
	private QuestionsRepository questionRepo;
	
	@Autowired
	private AnswersRepository answersRepo;
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ResultRepository resultRepo;
	
	@GetMapping("start-exam")
	public String startExam(@RequestParam("id") Long id, Model model, HttpSession session) {
	    try {
	    	String username = (String) session.getAttribute("username");
		    String fullname = (String) session.getAttribute("fullname");
		    String msv = (String) session.getAttribute("msv");
			if(msv != null) {
				if(username != null) {
					// Check đây là user
					Exam exam = examRepo.findById(id).orElse(null);
			        if (exam != null) {
			            String mamon = exam.getMamon();
			            int thoigianlambai = exam.getTglambai();
			            List<Questions> questions = questionRepo.findAllByMamon(mamon);
			            List<Answers> allAnswers = new ArrayList<>();
			            for (Questions question : questions) {
			                Long idcauhoi = question.getIdcauhoi();
			                List<Answers> answers = answersRepo.findAllAnswersByIdcauhoi(idcauhoi);
			                allAnswers.addAll(answers);
			            }
			            model.addAttribute("thoigianlambai", thoigianlambai);
			            model.addAttribute("questions", questions);
			            model.addAttribute("answers", allAnswers);
			            model.addAttribute("fullname", fullname);
			        }  
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
	    }
	    return "exam/exam"; 
	}
	
	
	@GetMapping("user-result")
	public String startExam(
			@RequestParam("id") Long id,
			@RequestParam("score") int score,
			@RequestParam("msv") String msv,
			@RequestParam("ht") String ht,
			HttpSession session,
			Model model) {
		 try {
			    String username = (String) session.getAttribute("username");
			    String fullname = (String) session.getAttribute("fullname");
			    String msv1 = (String) session.getAttribute("msv");
				if(msv1 != null) {
					if(username != null) {
						// Check đây là user
						 Exam exam = examRepo.findById(id).orElse(null);
					        if (exam != null) {
					        	Student student = studentRepo.findByStudentId(msv);
					            String mamon = exam.getMamon();
					            Long idstudent = student.getIdsinhvien();
					            float diemSo = (float) score / exam.getTongsocau();
					            float diemSoRounded = (float) (Math.floor(diemSo * 10) / 10) * 10;
					            
					            Result result = new Result();
						        result.setIdkythi(id);
						        result.setIdsinhvien(idstudent);
						        result.setDiem(diemSoRounded);
						        result.setTraloi(ht);
						        
					            List<Questions> questions = questionRepo.findAllByMamon(mamon);
					            List<Answers> allAnswers = new ArrayList<>();
					            for (Questions question : questions) {
					                Long idcauhoi = question.getIdcauhoi();
					                List<Answers> answers = answersRepo.findAllAnswersByIdcauhoi(idcauhoi);
					                allAnswers.addAll(answers);
					            }
					            
					            List<Integer> indices = new ArrayList<>();
					            for (int i = 0; i < ht.length(); i++) {
					                if (ht.charAt(i) == '1') {
					                    indices.add(i % 4);
					                }
					            }
					            resultRepo.save(result);
						        model.addAttribute("fullname", fullname);
					            model.addAttribute("diemSoRounded", diemSoRounded);
					            model.addAttribute("exam", exam);
					            model.addAttribute("student", student);
					            model.addAttribute("questions", questions);
					            model.addAttribute("answers", allAnswers);
					            model.addAttribute("score", score);
					            model.addAttribute("ht", ht);
					            model.addAttribute("indices", indices);
					        }
					
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
		    }
		 return "exam/user-result"; 
	    
	}
	
	

	
	
	

}
