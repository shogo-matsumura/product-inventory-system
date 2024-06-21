package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.SmallCategory;
import com.example.demo.entity.Subcategory;
import com.example.demo.repository.LargeCategoryRepository;
import com.example.demo.repository.SmallCategoryRepository;
import com.example.demo.repository.SubcategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private LargeCategoryRepository largeCategoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private SmallCategoryRepository smallCategoryRepository;

    @Override
    public List<LargeCategory> getAllCategories() {
        return largeCategoryRepository.findAll();
    }

    @Override
    public List<Subcategory> getSubcategoriesByLargeCategoryId(Integer largeCategoryId) {
        return subcategoryRepository.findByLargeCategory_LargeCategoryId(largeCategoryId);
    }

    @Override
    public List<SmallCategory> getSmallCategoriesBySubcategoryId(Integer subcategoryId) {
        return smallCategoryRepository.findBySubcategory_SubcategoryId(subcategoryId);
    }
}
