package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.SmallCategory;
import com.example.demo.entity.Subcategory;
import com.example.demo.repository.SmallCategoryRepository;
import com.example.demo.repository.SubcategoryRepository;

@Controller
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private SmallCategoryRepository smallCategoryRepository;

    @GetMapping("/subcategory/details/{id}")
    public String viewSubcategoryDetails(@PathVariable("id") Integer id, Model model) {
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        List<SmallCategory> smallCategories = smallCategoryRepository.findBySubcategory_SubcategoryId(id);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("smallCategories", smallCategories);
        return "subcategory-details";
    }
}

