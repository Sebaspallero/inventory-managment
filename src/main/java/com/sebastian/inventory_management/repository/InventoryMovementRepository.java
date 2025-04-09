package com.sebastian.inventory_management.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO;
import com.sebastian.inventory_management.model.InventoryMovement;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findByProductId(Long productId);

    List<InventoryMovement> findByUserId(Long userId);

    @Query("SELECT m FROM InventoryMovement m WHERE m.timestamp BETWEEN :startDate AND :endDate")
    List<InventoryMovement> findMovementsBetweenDates(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("""
                SELECT new com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO(
                    MONTH(m.timestamp),
                    m.type,
                    SUM(m.quantity)
                )
                FROM InventoryMovement m
                GROUP BY MONTH(m.timestamp), m.type
            """)
    List<InventoryMovementResponseDTO> getMonthlyMovementsSummary();
}
