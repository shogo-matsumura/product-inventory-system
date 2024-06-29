package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.ProductStore;

public interface ProductStoreService {
    Page<ProductStore> getProductStoresByNameAndStoreId(String productName, Integer storeId, int page, int size);

    boolean addStock(Integer productStoreId, Integer storeId, Integer quantity);

    List<ProductStore> getProductStoresByStoreId(Integer storeId);

    ProductStore getProductStoreById(Integer productStoreId);
}
