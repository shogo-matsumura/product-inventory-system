package com.example.demo.entity;

public class OrderRequestDTO {
    private Integer productStoreId;
    private Integer quantity;

    // Getters and setters
    public Integer getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(Integer productStoreId) {
        this.productStoreId = productStoreId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
