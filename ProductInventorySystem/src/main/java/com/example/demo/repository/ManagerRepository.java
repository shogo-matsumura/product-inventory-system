package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findByEmail(String email);
    List<Manager> findByStoreId(Integer storeId);
}