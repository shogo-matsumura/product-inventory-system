package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Manufacturer;
import com.example.demo.service.ManufacturerService;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturer-management")
    public String showManufacturerManagement(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "manufacturer-management";
    }

    @GetMapping("/manufacturer/details/{id}")
    public String showManufacturerDetails(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!manufacturerService.hasPermission(email, 1)) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }

        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-details";
    }

    @GetMapping("/manufacturer/new")
    public String showCreateManufacturerForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!manufacturerService.hasPermission(email, 1)) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }
        return "manufacturer-create";
    }

    @PostMapping("/manufacturer/create")
    public String createManufacturer(@RequestParam("manufacturerName") String manufacturerName, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!manufacturerService.hasPermission(email, 1)) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }
        manufacturerService.createManufacturer(manufacturerName);
        return "redirect:/manufacturer-management";
    }

    @PostMapping("/manufacturer/update")
    public String updateManufacturer(@RequestParam("manufacturerId") Integer manufacturerId,
                                     @RequestParam("manufacturerName") String manufacturerName, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!manufacturerService.hasPermission(email, 1)) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }

        manufacturerService.updateManufacturer(manufacturerId, manufacturerName);
        return "redirect:/manufacturer-management";
    }

    @PostMapping("/manufacturer/delete")
    public String deleteManufacturer(@RequestParam("manufacturerId") Integer manufacturerId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!manufacturerService.hasPermission(email, 1)) {
            model.addAttribute("error", "権限がありません。");
            return "access-denied";
        }
        manufacturerService.deleteManufacturer(manufacturerId);
        return "redirect:/manufacturer-management";
    }
}
