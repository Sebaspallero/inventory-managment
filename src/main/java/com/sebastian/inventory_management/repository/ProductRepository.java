package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
