package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;

@Controller
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/dashboard/{storeId}")
    public String storeManagement(@PathVariable("storeId") Integer storeId, Model model) {
        Store store = storeRepository.findByStoreId(storeId);
        
        if (store != null) {
            model.addAttribute("storeName", store.getStoreName());
            model.addAttribute("address", store.getAddress());
        }
        
        return "store-management";
    }
}
