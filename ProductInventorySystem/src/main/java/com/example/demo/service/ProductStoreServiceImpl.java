package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductStore;
import com.example.demo.repository.ProductStoreRepository;

@Service
public class ProductStoreServiceImpl implements ProductStoreService {

    @Autowired
    private ProductStoreRepository productStoreRepository;

    @Override
    public Page<ProductStore> getProductStoresByNameAndStoreId(String productName, Integer storeId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productStoreRepository.findByProduct_ProductNameContainingAndStore_StoreId(productName, storeId, pageRequest);
    }

    @Override
    public boolean addStock(Integer productStoreId, Integer storeId, Integer quantity) {
        ProductStore productStore = productStoreRepository.findByProductStoreIdAndStore_StoreId(productStoreId, storeId);
        if (productStore != null) {
            productStore.setStockQuantity(productStore.getStockQuantity() + quantity);
            productStoreRepository.save(productStore);
            return true;
        }
        return false;
    }

    @Override
    public List<ProductStore> getProductStoresByStoreId(Integer storeId) {
        return productStoreRepository.findByStore_StoreId(storeId);
    }

    @Override
    public ProductStore getProductStoreById(Integer productStoreId) {
        return productStoreRepository.findById(productStoreId).orElse(null);
    }
}
