package com.nhommot.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhommot.thitracnghiem.models.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
	Exam findOneByMamon (String mamon);
}
