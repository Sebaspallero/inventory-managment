package com.sebastian.inventory_management.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementRequestDTO;
import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO;

public interface IInventoryMovementService {
    InventoryMovementResponseDTO addMovement(InventoryMovementRequestDTO movement);
    InventoryMovementResponseDTO getMovementById(Long id);
    List<InventoryMovementResponseDTO> getAllMovements();
    List<InventoryMovementResponseDTO> findByProductId(Long productId);
    List<InventoryMovementResponseDTO> findByUserId(Long userId);
    List<InventoryMovementResponseDTO> findMovementsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
