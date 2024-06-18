package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.entity.ManagerDto;
import com.example.demo.repository.ManagerRepository;

//必要なクラスをインポートします
import jakarta.transaction.Transactional;

@Service
public class ManagerServiceImpl implements UserDetailsService {

 @Autowired
 private ManagerRepository managerRepository;

 @Autowired
 private PasswordEncoder passwordEncoder;

 @Override
 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     Manager manager = managerRepository.findByEmail(email);
     if (manager == null) {
         throw new UsernameNotFoundException("User not found with email: " + email);
     }
     return new ManagerService(manager);
 }

 public Manager findByEmail(String email) {
     return managerRepository.findByEmail(email);
 }

 @Transactional
 public void save(ManagerDto managerDto) {
     Manager manager = new Manager();
     manager.setEmail(managerDto.getEmail());
     manager.setPassword(passwordEncoder.encode(managerDto.getPassword()));
     managerRepository.save(manager);
 }
}
