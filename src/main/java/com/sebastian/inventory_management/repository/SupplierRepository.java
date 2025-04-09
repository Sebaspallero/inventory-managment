package com.sebastian.inventory_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sebastian.inventory_management.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
        
    Optional <Supplier> findByName(String name);
    Optional <Supplier> findByContactEmail(String contactEmail);
    @Query("SELECT COUNT(s) FROM Supplier s")
    Long countActiveSuppliers();

} 
