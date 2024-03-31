package com.nhommot.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhommot.thitracnghiem.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
