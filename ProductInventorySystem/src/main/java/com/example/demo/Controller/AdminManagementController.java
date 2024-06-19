package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Manager;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Position;
import com.example.demo.entity.Store;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.StoreRepository;

@Controller
public class AdminManagementController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin-management")
    public String viewAdminManagement(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager loggedInManager = managerRepository.findByEmail(email);

        if (loggedInManager != null) {
            List<Manager> managers = managerRepository.findByStoreId(loggedInManager.getStoreId());
            model.addAttribute("managers", managers);
        }

        return "admin-management";
    }

    @GetMapping("/admin-management/details/{id}")
    public String viewManagerDetails(@PathVariable("id") Integer id, Model model) {
        Manager manager = managerRepository.findById(id).orElse(null);

        if (manager != null) {
            model.addAttribute("manager", manager);
        }

        return "manager-details";
    }

    @GetMapping("/admin-management/register")
    public String registerManagerForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager loggedInManager = managerRepository.findByEmail(email);

        if (loggedInManager.getPermissionId() != 1) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }

        List<Store> stores = storeRepository.findAll();
        List<Permission> permissions = permissionRepository.findAll();
        List<Position> positions = positionRepository.findAll();

        model.addAttribute("stores", stores);
        model.addAttribute("permissions", permissions);
        model.addAttribute("positions", positions);
        model.addAttribute("manager", new Manager());

        return "register-manager";
    }

    @PostMapping("/admin-management/register")
    public String registerManager(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        managerRepository.save(manager);
        return "redirect:/admin-management";
    }

    @GetMapping("/admin-management/edit/{id}")
    public String editManagerForm(@PathVariable("id") Integer id, Model model) {
        Manager manager = managerRepository.findById(id).orElse(null);
        if (manager != null) {
            model.addAttribute("manager", manager);
        }
        return "edit-manager";
    }

    @PostMapping("/admin-management/edit")
    public String updateManager(Manager updatedManager) {
        Manager manager = managerRepository.findById(updatedManager.getId()).orElse(null);
        if (manager != null) {
            manager.setFirstName(updatedManager.getFirstName());
            manager.setLastName(updatedManager.getLastName());
            manager.setEmail(updatedManager.getEmail());
            manager.setPhoneNumber(updatedManager.getPhoneNumber());
            managerRepository.save(manager);
        }
        return "redirect:/admin-management";
    }

    @PostMapping("/admin-management/delete/{id}")
    public String deleteManager(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Manager loggedInManager = managerRepository.findByEmail(email);

        if (loggedInManager.getPermissionId() != 1) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }

        managerRepository.deleteById(id);
        return "redirect:/admin-management";
    }
}
