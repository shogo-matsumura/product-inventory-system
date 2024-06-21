package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.SmallCategory;
import com.example.demo.entity.Subcategory;

public interface CategoryService {
    List<LargeCategory> getAllCategories();
    List<Subcategory> getSubcategoriesByLargeCategoryId(Integer largeCategoryId);
    List<SmallCategory> getSmallCategoriesBySubcategoryId(Integer subcategoryId);
}