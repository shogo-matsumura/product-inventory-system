package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //特定の大カテゴリ、中カテゴリ、小カテゴリ、および検索文字列に基づいて商品を検索する
    @Query("SELECT p FROM Product p WHERE " +
           "(:largeCategoryId IS NULL OR p.smallCategory.subcategory.largeCategory.largeCategoryId = :largeCategoryId) AND " +
           "(:subCategoryId IS NULL OR p.smallCategory.subcategory.subcategoryId = :subCategoryId) AND " +
           "(:smallCategoryId IS NULL OR p.smallCategory.smallCategoryId = :smallCategoryId) AND " +
           "(:search IS NULL OR p.productName LIKE %:search%)")
    Page<Product> searchProducts(Integer largeCategoryId, Integer subCategoryId, Integer smallCategoryId, String search, Pageable pageable);
}
