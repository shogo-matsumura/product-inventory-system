package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.Subcategory;
import com.example.demo.repository.LargeCategoryRepository;
import com.example.demo.repository.SubcategoryRepository;

@Controller
public class CategoryManagementController {

    @Autowired
    private LargeCategoryRepository largeCategoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping("/category-management")
    public String viewCategoryManagement(Model model) {
        List<LargeCategory> categories = largeCategoryRepository.findAll();
        for (LargeCategory category : categories) {
            List<Subcategory> subcategories = subcategoryRepository.findByLargeCategory_LargeCategoryId(category.getLargeCategoryId());
            category.setSubcategories(subcategories);
        }
        model.addAttribute("categories", categories);
        return "category-management";
    }

    @GetMapping("/category-management/details/{id}")
    public String viewCategoryDetails(@PathVariable("id") Integer id, Model model) {
        LargeCategory largeCategory = largeCategoryRepository.findById(id).orElse(null);
        List<Subcategory> subcategories = subcategoryRepository.findByLargeCategory_LargeCategoryId(id);
        model.addAttribute("largeCategory", largeCategory);
        model.addAttribute("subcategories", subcategories);
        return "category-details";
    }
}
