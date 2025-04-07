package com.sebastian.inventory_management.DTO.InventoryMovement;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sebastian.inventory_management.enums.MovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementResponseDTO {
    
    private Long id;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    
    private Long productId;
    private String productName;
    private Long userId;
    private String username;
    private MovementType type;
}
