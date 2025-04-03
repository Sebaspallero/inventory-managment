package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.InventoryMovement;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

}
