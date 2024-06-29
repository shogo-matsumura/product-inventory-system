package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SmallCategory;

@Repository
public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {
    List<SmallCategory> findBySubcategory_SubcategoryId(Integer subcategoryId);
}
