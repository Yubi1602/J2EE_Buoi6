package com.example.bai2_letranbaokha.repository;

import com.example.bai2_letranbaokha.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}