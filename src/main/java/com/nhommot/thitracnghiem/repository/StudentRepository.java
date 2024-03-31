package com.nhommot.thitracnghiem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nhommot.thitracnghiem.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByUsername(String username);
	Student findByStudentId(String studentId);
}
