package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductStore;

public interface ProductStoreRepository extends JpaRepository<ProductStore, Integer> {
    Page<ProductStore> findByProduct_ProductNameContainingAndStore_StoreId(String productName, Integer storeId, Pageable pageable);
    ProductStore findByProductStoreIdAndStore_StoreId(Integer productStoreId, Integer storeId);

    List<ProductStore> findByStore_StoreId(Integer storeId);
}
