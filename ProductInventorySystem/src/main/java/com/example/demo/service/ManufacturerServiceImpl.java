package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.ManufacturerRepository;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Integer id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer.orElse(null);
    }

    @Override
    public void updateManufacturer(Integer manufacturerId, String manufacturerName) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(manufacturerId);
        if (optionalManufacturer.isPresent()) {
            Manufacturer manufacturer = optionalManufacturer.get();
            manufacturer.setManufacturerName(manufacturerName);
            manufacturerRepository.save(manufacturer);
        }
    }

    @Override
    public void deleteManufacturer(Integer manufacturerId) {
        manufacturerRepository.deleteById(manufacturerId);
    }

    @Override
    public void createManufacturer(String manufacturerName) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerName(manufacturerName);
        LocalDateTime now = LocalDateTime.now();
        manufacturer.setCreatedAt(now);
        manufacturer.setUpdatedAt(now);
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public boolean hasPermission(String email, int requiredPermission) {
        Manager manager = managerRepository.findByEmail(email);
        return manager != null && manager.getPermissionId() == requiredPermission;
    }
}
