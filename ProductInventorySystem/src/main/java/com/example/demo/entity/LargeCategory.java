package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "large_categories")
public class LargeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "large_category_id")
    private Integer largeCategoryId;

    @Column(name = "large_category_name")
    private String largeCategoryName;

    @OneToMany(mappedBy = "largeCategory", fetch = FetchType.LAZY) 
    @JsonManagedReference
    private List<Subcategory> subcategories;

    // Getters and setters
    public Integer getLargeCategoryId() {
        return largeCategoryId;
    }

    public void setLargeCategoryId(Integer largeCategoryId) {
        this.largeCategoryId = largeCategoryId;
    }

    public String getLargeCategoryName() {
        return largeCategoryName;
    }

    public void setLargeCategoryName(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
