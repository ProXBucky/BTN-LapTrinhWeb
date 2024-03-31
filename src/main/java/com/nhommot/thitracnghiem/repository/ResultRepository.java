package com.nhommot.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nhommot.thitracnghiem.models.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
	@Transactional
	void deleteResultByIdsinhvien(Long idsinhvien);

	void deleteAllByIdkythi(Long idkythi);
	
	List<Result> findAllByIdkythi(Long idkythi);
	
	List<Result> findAllByIdsinhvien(Long idsinhvien);
	
	Result findOneByIdkythi(Long idkythi);
}
