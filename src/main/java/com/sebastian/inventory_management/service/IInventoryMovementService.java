package com.sebastian.inventory_management.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sebastian.inventory_management.model.InventoryMovement;

public interface IInventoryMovementService {
    InventoryMovement addMovement(InventoryMovement movement);
    void deleteMovement(Long id);
    InventoryMovement getMovementById(Long id);
    List<InventoryMovement> getAllMovements();
    List<InventoryMovement> getMovementsByProduct(Long productId);
    List<InventoryMovement> findByProductId(Long productId);
    List<InventoryMovement> findByUserId(Long userId);
    List<InventoryMovement> findMovementsBetweenDates(String startDate, String endDate);
     List<InventoryMovement> findMovementsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
