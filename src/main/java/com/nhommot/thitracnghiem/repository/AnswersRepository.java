package com.nhommot.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhommot.thitracnghiem.models.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
	void deleteAllAnswerByIdcauhoi(Long idcauhoi);
	List<Answers> findAllAnswersByIdcauhoi(Long idcauhoi);
}
