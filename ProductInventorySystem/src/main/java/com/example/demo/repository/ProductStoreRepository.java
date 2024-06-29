package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ProductStore;

public interface ProductStoreRepository extends JpaRepository<ProductStore, Integer> {

    @Query("SELECT ps FROM ProductStore ps JOIN FETCH ps.product WHERE ps.product.productName LIKE %:productName% AND ps.store.storeId = :storeId")
    Page<ProductStore> findByProduct_ProductNameContainingAndStore_StoreId(@Param("productName") String productName, @Param("storeId") Integer storeId, Pageable pageable);

    ProductStore findByProductStoreIdAndStore_StoreId(Integer productStoreId, Integer storeId);

    List<ProductStore> findByStore_StoreId(Integer storeId);
}
