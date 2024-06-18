package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Controller
public class DashboardController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager manager = managerRepository.findByEmail(email);
        
        if (manager != null) {
            model.addAttribute("storeId", manager.getStoreId());
        }
        
        return "dashboard";
    }
}
