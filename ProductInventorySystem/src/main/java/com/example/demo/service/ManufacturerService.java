package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Manufacturer;

public interface ManufacturerService {
    List<Manufacturer> getAllManufacturers();
    Manufacturer getManufacturerById(Integer id); 
    void updateManufacturer(Integer manufacturerId, String manufacturerName); 
    void deleteManufacturer(Integer manufacturerId); 
    void createManufacturer(String manufacturerName);
    boolean hasPermission(String email, int requiredPermissionId);
}
