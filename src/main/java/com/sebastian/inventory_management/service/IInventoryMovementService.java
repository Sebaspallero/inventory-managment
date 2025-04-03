package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.InventoryMovement;

public interface IInventoryMovementService {
    InventoryMovement addMovement(InventoryMovement movement);
    void deleteMovement(Long id);
    InventoryMovement getMovementById(Long id);
    List<InventoryMovement> getAllMovements();
    List<InventoryMovement> getMovementsByProduct(Long productId);
}
