package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProducts(Integer largeCategoryId, Integer subCategoryId, Integer smallCategoryId, String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        if (search == null || search.isEmpty()) {
            return Page.empty(pageRequest); // 検索文字列が空白の場合、検索結果が表示されない
        }
        return productRepository.searchProducts(largeCategoryId, subCategoryId, smallCategoryId, search, pageRequest);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId.longValue()).orElse(null);
    }
}
