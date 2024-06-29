package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Controller
public class ProfileController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager manager = managerRepository.findByEmail(email);

        if (manager != null) {
            model.addAttribute("manager", manager);
        }

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager manager = managerRepository.findByEmail(email);

        if (manager != null) {
            model.addAttribute("manager", manager);
        }

        return "profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(Manager updatedManager) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager manager = managerRepository.findByEmail(email);

        if (manager != null) {
            manager.setFirstName(updatedManager.getFirstName());
            manager.setLastName(updatedManager.getLastName());
            manager.setEmail(updatedManager.getEmail());
            manager.setPhoneNumber(updatedManager.getPhoneNumber());
            managerRepository.save(manager);
        }

        return "redirect:/profile";
    }
}
