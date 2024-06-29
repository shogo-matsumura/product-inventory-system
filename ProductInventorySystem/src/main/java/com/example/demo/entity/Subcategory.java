package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subcategories")
public class Subcategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Integer subcategoryId;

    @Column(name = "subcategory_name")
    private String subcategoryName;

    @ManyToOne
    @JoinColumn(name = "large_category_id", nullable = false)
    @JsonBackReference
    private LargeCategory largeCategory;

    @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SmallCategory> smallCategories;

    // Getters and setters
    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public LargeCategory getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(LargeCategory largeCategory) {
        this.largeCategory = largeCategory;
    }

    public List<SmallCategory> getSmallCategories() {
        return smallCategories;
    }

    public void setSmallCategories(List<SmallCategory> smallCategories) {
        this.smallCategories = smallCategories;
    }
}
