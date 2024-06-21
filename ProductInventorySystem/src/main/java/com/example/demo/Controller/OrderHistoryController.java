package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Manager;
import com.example.demo.entity.OrderHistory;
import com.example.demo.entity.ProductStore;
import com.example.demo.entity.Store;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.ProductStoreService;
import com.example.demo.service.StoreService;

@Controller
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/order-history")
    public String showOrderHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager manager = managerRepository.findByEmail(email);
        Integer storeId = manager.getStoreId();

        Store store = storeService.getStoreById(storeId);
        List<ProductStore> productStores = productStoreService.getProductStoresByStoreId(storeId);
        List<OrderHistory> orderHistories = orderHistoryService.getOrderHistoriesByStoreId(storeId);

        store.setProductStores(productStores);
        store.setOrderHistories(orderHistories);

        model.addAttribute("stores", List.of(store));

        return "order-history";
    }
}
