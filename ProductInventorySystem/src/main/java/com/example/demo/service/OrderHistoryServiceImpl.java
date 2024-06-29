package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderHistory;
import com.example.demo.repository.OrderHistoryRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<OrderHistory> getOrderHistoriesByStoreId(Integer storeId) {
        List<OrderHistory> orderHistories = orderHistoryRepository.findByStore_StoreId(storeId);
        //System.out.println("OrderHistories retrieved: " + orderHistories.size());//デバッグのため
        return orderHistories;
    }

    @Override
    public void saveOrderHistory(OrderHistory orderHistory) {
        orderHistoryRepository.save(orderHistory);
    }
}
