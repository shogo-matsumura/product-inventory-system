package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.OrderHistory;

public interface OrderHistoryService {
    List<OrderHistory> getOrderHistoriesByStoreId(Integer storeId);
    void saveOrderHistory(OrderHistory orderHistory);
}
