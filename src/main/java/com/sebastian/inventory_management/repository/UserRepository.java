package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
} 
