package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Product;

public interface ProductService {
    Page<Product> searchProducts(Integer largeCategoryId, Integer subCategoryId, Integer smallCategoryId, String search, int page, int size);
    Product getProductById(Integer productId);
}
