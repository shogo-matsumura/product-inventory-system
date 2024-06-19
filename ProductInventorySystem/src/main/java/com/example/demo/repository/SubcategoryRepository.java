package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    List<Subcategory> findByLargeCategory_LargeCategoryId(Integer largeCategoryId);
}


