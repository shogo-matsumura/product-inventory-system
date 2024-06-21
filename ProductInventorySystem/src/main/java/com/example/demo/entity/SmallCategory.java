package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "small_categories")
public class SmallCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "small_category_id")
    private Integer smallCategoryId;

    @Column(name = "small_category_name")
    private String smallCategoryName;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    @JsonBackReference
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "large_category_id", nullable = false) 
    @JsonBackReference
    private LargeCategory largeCategory;

    // Getters and setters
    public Integer getSmallCategoryId() {
        return smallCategoryId;
    }

    public void setSmallCategoryId(Integer smallCategoryId) {
        this.smallCategoryId = smallCategoryId;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public LargeCategory getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(LargeCategory largeCategory) {
        this.largeCategory = largeCategory;
    }
}
