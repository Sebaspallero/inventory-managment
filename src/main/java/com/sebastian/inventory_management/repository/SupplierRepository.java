package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {


} 
