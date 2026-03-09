package com.example.bai2_letranbaokha.repository;

import com.example.bai2_letranbaokha.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}