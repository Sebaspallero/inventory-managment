package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebastian.inventory_management.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
        
    Supplier findByName(String name);
    Supplier findByContactEmail(String contactEmail);

} 
