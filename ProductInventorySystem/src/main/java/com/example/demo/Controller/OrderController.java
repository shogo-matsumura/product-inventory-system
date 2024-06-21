package com.example.demo.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Manager;
import com.example.demo.entity.OrderHistory;
import com.example.demo.entity.OrderRequestDTO;
import com.example.demo.entity.ProductStore;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.ProductStoreService;

@RestController
public class OrderController {

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private ManagerRepository managerRepository;
    //API実装
    @PostMapping("/api/order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Integer storeId = getStoreIdByEmail(email);

        ProductStore productStore = productStoreService.getProductStoreById(orderRequest.getProductStoreId());
        if (productStore == null || !productStore.getStore().getStoreId().equals(storeId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product store ID or store ID.");
        }

        // 在庫の追加
        boolean success = productStoreService.addStock(orderRequest.getProductStoreId(), storeId, orderRequest.getQuantity());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add stock.");
        }

        // 発注履歴の保存
        Integer orderQuantity = orderRequest.getQuantity();
        BigDecimal totalAmount = productStore.getSellingPrice().multiply(BigDecimal.valueOf(orderQuantity));

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setManager(managerRepository.findByEmail(email));
        orderHistory.setProduct(productStore.getProduct());
        orderHistory.setStore(productStore.getStore());
        orderHistory.setOrderQuantity(orderQuantity);
        orderHistory.setTotalAmount(totalAmount);
        orderHistory.setCreatedAt(LocalDateTime.now());
        orderHistory.setUpdatedAt(LocalDateTime.now());

        orderHistoryService.saveOrderHistory(orderHistory);

        return ResponseEntity.ok("発注完了しました.");//作成完了後のポップアップ
    }

    private Integer getStoreIdByEmail(String email) {
        Manager manager = managerRepository.findByEmail(email);
        if (manager == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager not found for email: " + email);
        }
        return manager.getStoreId();
    }
}
