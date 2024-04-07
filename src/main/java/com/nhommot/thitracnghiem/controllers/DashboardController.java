package com.nhommot.thitracnghiem.controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhommot.thitracnghiem.dto.CreateExamDto;
import com.nhommot.thitracnghiem.dto.CreateUserDto;
import com.nhommot.thitracnghiem.models.Answers;
import com.nhommot.thitracnghiem.models.ChartData;
import com.nhommot.thitracnghiem.models.Dataset;
import com.nhommot.thitracnghiem.models.Exam;
import com.nhommot.thitracnghiem.models.Questions;
import com.nhommot.thitracnghiem.models.Result;
import com.nhommot.thitracnghiem.models.Student;
import com.nhommot.thitracnghiem.models.Subject;
import com.nhommot.thitracnghiem.repository.AnswersRepository;
import com.nhommot.thitracnghiem.repository.ExamRepository;
import com.nhommot.thitracnghiem.repository.QuestionsRepository;
import com.nhommot.thitracnghiem.repository.ResultRepository;
import com.nhommot.thitracnghiem.repository.StudentRepository;
import com.nhommot.thitracnghiem.repository.SubjectRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ExamRepository examRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private QuestionsRepository questionRepo;

    @Autowired
    private AnswersRepository answerRepo;
    
    @Autowired
    private ResultRepository resultRepo;
	
    @GetMapping({"", "/"})
    public String showUserList(Model model, HttpSession session) {
    	try {
    		
    			String username = (String) session.getAttribute("username");
    		    String fullname = (String) session.getAttribute("fullname");
    		    String msv = (String) session.getAttribute("msv");
    			if(msv == null) {
    				if(username != null) {
    					// Check đây là admin
    					List<Student> students = studentRepo.findAll();
    		             model.addAttribute("students", students);
    		             
    		             long studentCount = studentRepo.count();
    		             model.addAttribute("studentCount", studentCount);
    		             
    		             List<Exam> exams = examRepo.findAll();
    		             
    		             for (Exam exam : exams) {
    		                 float diemTong = 0;
    		                 int resultCount1 = 0;
    		                 Set<Long> uniqueIdsinhviens = new HashSet<>();

    		                 for (Result result : resultRepo.findAllByIdkythi(exam.getIdkythi())) {
    		                     uniqueIdsinhviens.add(result.getIdsinhvien()); 
    		                     diemTong += result.getDiem();
    		                     resultCount1++;
    		                 }

    		                 int resultCount = uniqueIdsinhviens.size(); 
    		                 
    		                
    		                 if (resultCount == 0) {
    		                     exam.setAverageScore(0.0f); 
    		                     exam.setResultsCount(0.0f);
    		                 } 
    		                 else {
    		                 
    		                 	float tilehoanthanh = ((float) resultCount / studentCount) * 100;
    		                 	float formattedResult = ((float) ((int) (tilehoanthanh * 100))) / 100;
    		                     exam.setResultsCount(formattedResult); 
    		                    
    		                     
    		                     float diemSoTB = diemTong / resultCount1;
    		                     float diemSoTBRounded = (float) (Math.floor(diemSoTB * 10) / 10);
    		                     exam.setAverageScore(diemSoTBRounded);
    		                 }
    		                 examRepo.save(exam);
    		             }
    		             List<Exam> examss = examRepo.findAll();
    		             model.addAttribute("fullname", fullname);
    		             model.addAttribute("exams", examss);
    		             return "dashboard/dashboard";
    				
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
    
    @PostMapping("/create-exam")
    public String createNewExam(@ModelAttribute CreateExamDto examDto ,Model model, HttpSession session) throws Exception {
    	
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				    String mamon = examDto.getMaMon();
    			        String tenkythi = examDto.getTenKythi();
    			        int tglambai = examDto.getTglambai();
    			    	int tongsocau = examDto.getTongsocau();
    			    	String tgmode = examDto.getTgmode();
    			    	String tgdongde = examDto.getTgdongde();
    			    	String tenmon = examDto.getTenMon();
    			    	
    			    	Date dongDe = null;
    			    	Date moDe = null;
    			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    			        try {
    			        	dongDe = formatter.parse(tgdongde);
    			        	moDe = formatter.parse(tgmode);
    			        } catch (ParseException e) {
    			             e.printStackTrace();
    			             throw new Exception("Invalid date format");
    			        }
    			        
    			        Subject subject = new Subject();
    			    	subject.setMamon(mamon);
    			    	subject.setTenmon(tenmon);
    			    	subjectRepo.save(subject);
    			        
    			    	Exam examm = new Exam();
    			    	examm.setMamon(mamon);
    			    	examm.setTenkythi(tenkythi);
    			    	examm.setTglambai(tglambai);
    			    	examm.setTongsocau(tongsocau);
    			    	examm.setTgmode(moDe);
    			    	examm.setTgdongde(dongDe);
    			    	examm.setSubject(subject);
    			    	
    			    	
    			    	int index = 0;
    			    	for (Questions question : examDto.getQuestions()) {
    			            Questions ques = new Questions();
    			            ques.setMamon(mamon);
    			            ques.setNoidung(question.getNoidung());
    			            ques.setSubject(subject);
    			            questionRepo.save(ques);
    			            
    			            List<Answers> listAnswers = examDto.getAnswers();
    			            for(int i = 0 ; i < 4 ; i++) {
    			            	Answers ans = new Answers();
    			            	ans.setIdcauhoi(ques.getIdcauhoi());
    			            	ans.setNoidung(listAnswers.get(index).getNoidung());
    			            	if(listAnswers.get(index).getDungSai() == null) {
    			            		ans.setDungSai(false);
    			            	}else {
    			            	 	ans.setDungSai(true);
    			            	}
    			            	ans.setQuestion(ques);
    			            	answerRepo.save(ans);
    			            	index++;
    			            }
    			            String dapanString = "";
    			           
    			            for(Answers ans : listAnswers) {
    			            	if(ans.getDungSai() == null) {
    			            		dapanString += "0";
    			            	}else {
    			            		dapanString += "1";
    			            	}
    			            }
    			            examm.setKetqua(dapanString);
    			            examRepo.save(examm);
    			        }
    			    	return "redirect:/dashboard";
    			
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
    
    

    @DeleteMapping("delete-exam")
   @Transactional
    public String deleteExamById(@RequestParam("id") Long id, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				Exam exam = examRepo.findById(id).orElse(null);
    	            if (exam != null) {
    	                String mamon = exam.getMamon();
    	                Long idkythi = exam.getIdkythi();
    	                List<Questions> ques = questionRepo.findAllByMamon(mamon);
    	                
    	                resultRepo.deleteAllByIdkythi(idkythi);
    	                
    	                // Xóa câu trả lời liên quan đến câu hỏi
    	                for (Questions que : ques) {
    	                    Long idQues = que.getIdcauhoi();
    	                    answerRepo.deleteAllAnswerByIdcauhoi(idQues);
    	                }
    	                // Xóa câu hỏi
    	                questionRepo.deleteAllQuestionByMamon(mamon);
    	                
    	                // Xóa đối tượng Exam
    	                examRepo.delete(exam);

    	                // Xóa môn học
    	                subjectRepo.deleteById(mamon);
    	            }
    			} 
    			else {
    			     return "redirect:/login";    	 
    			}
    		}
    		else {
    				 return "redirect:/404"; 
    		}	
    		return "redirect:/dashboard";
    	} 
    	catch (Exception e) {
    	    e.printStackTrace();
    	    return "redirect:/404"; 
    	}
                    
    }

    
    @DeleteMapping("/delete-user")
    public String deleteUserById(@RequestParam("id") Long id, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				resultRepo.deleteResultByIdsinhvien(id);
    		    	studentRepo.deleteById(id);
    		    	return "redirect:/dashboard";
    			
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
    
    @GetMapping("/student-result")
	public String getResultPage(Model model, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				List<Student> students = studentRepo.findAll();
    				model.addAttribute("students", students);
    				return "dashboard/result";   
    			
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
    
    @GetMapping("/statistic-page")
	public String getStatisticPage(Model model, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				List<Subject> subjects = subjectRepo.findAll();
    				model.addAttribute("subjects",subjects);
    				
    				
    				 List<Student> students = studentRepo.findAll();
    			        model.addAttribute("students", students);
    			        
    			        long studentCount = studentRepo.count();
    			        model.addAttribute("studentCount", studentCount);
    			        
    			        List<Exam> exams = examRepo.findAll();
    			        
    			        for (Exam exam : exams) {
    			            float diemTong = 0;
    			            int resultCount1 = 0;
    			            Set<Long> uniqueIdsinhviens = new HashSet<>();

    			            for (Result result : resultRepo.findAllByIdkythi(exam.getIdkythi())) {
    			                uniqueIdsinhviens.add(result.getIdsinhvien()); 
    			                diemTong += result.getDiem();
    			                resultCount1++;
    			            }

    			            int resultCount = uniqueIdsinhviens.size(); 
    			            
    			           
    			            if (resultCount == 0) {
    			                exam.setAverageScore(0.0f); 
    			                exam.setResultsCount(0.0f);
    			            } 
    			            else {
    			            
    			            	float tilehoanthanh = ((float) resultCount / studentCount) * 100;
    			            	float formattedResult = ((float) ((int) (tilehoanthanh * 100))) / 100;
    			                exam.setResultsCount(formattedResult); 
    			               
    			                
    			                float diemSoTB = diemTong / resultCount1;
    			                float diemSoTBRounded = (float) (Math.floor(diemSoTB * 10) / 10);
    			                exam.setAverageScore(diemSoTBRounded);
    			            }
    			            examRepo.save(exam);
    			        }
    			        List<Exam> examss = examRepo.findAll();
    			        model.addAttribute("exams", examss);
    				
    				return "dashboard/statistic";
    			
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
    
    
    @PostMapping("/filter")
    @ResponseBody
    public ChartData filterData(@RequestParam("subject") String subject) {
       
    	List<Float> grades = new ArrayList<Float>();
    	if(subject.equals("all")) {
    		List<Result> results = resultRepo.findAll();
    		System.out.println(results);
    		if(results != null) {
    			for(Result result: results) {
    				grades.add(result.getDiem());
    			}
    		}
    	}
    	else {
    		Subject subj = subjectRepo.findById(subject).orElse(null);
    		if(subj != null) {
    			Exam exam = examRepo.findOneByMamon(subj.getMamon());
    			if(exam != null) {
    				List<Result> ress = resultRepo.findAllByIdkythi(exam.getIdkythi());
    				if(ress != null) {
    	    			for(Result result: ress) {
    	    				grades.add(result.getDiem());
    	    			}
    	    		}
    			}
    		}
    	}
    	
    	System.out.println("helo" + grades);
        
        
        // Tính toán phân phối điểm
        int[] distribution = calculateGradeDistribution(grades);
        
        // Tạo đối tượng ChartData và Dataset để lưu trữ dữ liệu biểu đồ
        ChartData chartData = new ChartData();
        chartData.setLabels(new String[]{"0-2", "3-5", "6-8", "9-10"});
        
        Dataset dataset = new Dataset();
        dataset.setLabel(subject); 
        dataset.setData(distribution); 
        chartData.setDatasets(new Dataset[]{dataset});
        
        return chartData;
    }

    private int[] calculateGradeDistribution(List<Float> grades) {
        int[] distribution = new int[4]; 
        for (float grade : grades) {
            if (grade >= 0 && grade <= 2.9) {
                distribution[0]++;
            } else if (grade >= 3 && grade <= 5.9) {
                distribution[1]++;
            } else if (grade >= 6 && grade <= 8.9) {
                distribution[2]++;
            } else if (grade >= 9 && grade <= 10) {
                distribution[3]++;
            }
        }
        return distribution;
    }
    
    
    @GetMapping("/view-result")
    public String viewResult(@RequestParam(value = "id", required = false) Long id,
                             Model model, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				 if (id != null) {
    			            try {
    			            	Student student = studentRepo.findById(id).orElse(null);
    			                List<Result> results = resultRepo.findAllByIdsinhvien(id);
    			                if (results != null) {
    			                    for (Result result : results) {
    			                        Exam exam = examRepo.findById(result.getIdkythi()).orElse(null);
    			                        Subject sbj = subjectRepo.findById(exam.getMamon()).orElse(null);
    			                        result.setTraloi(sbj.getTenmon());
    			                    }
    			                }
    			                model.addAttribute("student", student);
    			                model.addAttribute("results", results);
    			            } catch (Exception e) {
    			                e.printStackTrace();
    			            }
    			    }
    			        return "dashboard/detail-result";  
    			
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
    
    
    @GetMapping("/edit-user")
    public String showEditPage(Model model, @RequestParam("id") Long id, HttpSession session) {
    	try {
    		String username = (String) session.getAttribute("username");
    	    String fullname = (String) session.getAttribute("fullname");
    	    String msv = (String) session.getAttribute("msv");
    		if(msv == null) {
    			if(username != null) {
    				// Check đây là admin
    				  Student student = studentRepo.findById(id).orElse(null);
    			        if (student == null) {
    			            return "redirect:/edit-user";
    			        }
    			        
    			        String inputDate = student.getDob().toString();
    			        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    			        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    			        try {
    			            Date date = inputFormat.parse(inputDate);
    			            String formattedDate = outputFormat.format(date);
    			            
    			            CreateUserDto studentDto = new CreateUserDto();
    			            studentDto.setUsername(student.getUsername());
    			            studentDto.setClassname(student.getClassname());
    			            studentDto.setDobString(formattedDate);
    			            studentDto.setFullname(student.getFullname());
    			            studentDto.setPassword(student.getPassword());
    			            studentDto.setStudentId(student.getStudentId());

    			            model.addAttribute("student", student);
    			            model.addAttribute("studentDto", studentDto);
    			        } catch (ParseException e) {
    			            System.out.println("Error parsing date: " + e.getMessage());
    			        }
    			        
    			        

    			        return "dashboard/edit-user";
    			
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
    
    public Student findStudentByUsername(String username) {
		Student student = studentRepo.findByUsername(username);
		return student;
	}

    
	@PostMapping("/edit-user")
	public String editStudent(@RequestParam("id") Long id, @ModelAttribute CreateUserDto form, Model model, HttpSession session) throws Exception {
		try {
			String username1 = (String) session.getAttribute("username");
		    String fullname1 = (String) session.getAttribute("fullname");
		    String msv = (String) session.getAttribute("msv");
			if(msv != null) {
				if(username1 != null) {
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
			        
			       
			        Student optionalStudent = studentRepo.findById(id).orElse(null);
			        if (optionalStudent != null) {
			        	optionalStudent.setUsername(username);
			        	optionalStudent.setPassword(password);
			        	optionalStudent.setFullname(fullname);
			        	optionalStudent.setDob(dob);
			        	optionalStudent.setClassname(classname);
			        	optionalStudent.setStudentId(student_id);
			          
			            studentRepo.save(optionalStudent);
			        } else {
			            throw new Exception("Student not found with ID: " + id);
			        }
				    
				    return "redirect:/dashboard";   
				
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
	
	@GetMapping("/register-user-by-admin")
	public String getRegisterUserByAdminPage(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			String msv = (String) session.getAttribute("msv");
			if(msv == null) {
				if(username == null) {
					return "redirect:/";
				}else {
					return "dashboard/registerUserByAdmin";				
				}
			}
			else {
				return "redirect:/404";
			}
			
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	@GetMapping("add-exam")
	public String getCreateExamPage(HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			String msv = (String) session.getAttribute("msv");
			if(msv == null) {
				if(username == null) {
					return "redirect:/";
				}else {
					return "dashboard/addExam";				
				}				
			}
			else {
				return "redirect:/404";
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return "errorPage";
		}
	}
	
	   @GetMapping("/view-detail-result")
	    public String viewDetailResult(@RequestParam("id") Long idketqua, Model model, HttpSession session) {
		   try {
			    String username = (String) session.getAttribute("username");
			    String fullname = (String) session.getAttribute("fullname");
			    String msv1 = (String) session.getAttribute("msv");
				if(msv1 == null) {
					if(username != null) {
						// Check đây là user
							Result result = resultRepo.findById(idketqua).orElse(null);
							if(result != null) {
								Exam exam = examRepo.findById(result.getIdkythi()).orElse(null);
								if (exam != null) {
									Student student = studentRepo.findById(result.getIdsinhvien()).orElse(null);
									if(student != null) {
										String mamon = exam.getMamon();
										float diemSoRounded = result.getDiem();
										
										List<Questions> questions = questionRepo.findAllByMamon(mamon);
										List<Answers> allAnswers = new ArrayList<>();
										for (Questions question : questions) {
											Long idcauhoi = question.getIdcauhoi();
											List<Answers> answers = answerRepo.findAllAnswersByIdcauhoi(idcauhoi);
											allAnswers.addAll(answers);
										}
										String ht = result.getTraloi();
										
										List<Integer> indices = new ArrayList<>();
										for (int i = 0; i < ht.length(); i++) {
											if (ht.charAt(i) == '1') {
												indices.add(i % 4);
											}
										}
									
										model.addAttribute("fullname", fullname);
										model.addAttribute("diemSoRounded", diemSoRounded);
										model.addAttribute("exam", exam);
										model.addAttribute("student", student);
										model.addAttribute("questions", questions);
										model.addAttribute("answers", allAnswers);
//										model.addAttribute("score", score);
										model.addAttribute("ht", ht);
										model.addAttribute("indices", indices);
									}
								}
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
		 return "dashboard/view-detail-result"; 
	       
	  }
    
    
}




    

