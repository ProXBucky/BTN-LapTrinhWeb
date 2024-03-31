package com.nhommot.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhommot.thitracnghiem.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>  {
	 Admin findByUsername(String username);
}
