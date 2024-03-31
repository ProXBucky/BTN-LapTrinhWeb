package com.nhommot.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhommot.thitracnghiem.models.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
	List<Questions> findAllByMamon(String mamon);
	void deleteAllQuestionByMamon(String mamon);
}
