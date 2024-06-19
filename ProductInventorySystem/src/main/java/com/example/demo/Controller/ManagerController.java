package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@RestController
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/api/managers/{manager_id}")
    public Manager getManagerById(@PathVariable("manager_id") Integer id) { // 引数をIntegerに変更
        return managerRepository.findById(id).orElse(null);
    }
}
