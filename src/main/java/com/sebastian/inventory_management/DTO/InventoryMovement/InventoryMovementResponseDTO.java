package com.sebastian.inventory_management.DTO.InventoryMovement;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sebastian.inventory_management.enums.MovementType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Long totalQuantity;

    public InventoryMovementResponseDTO(Long id, LocalDateTime timestamp,
            Long productId, String productName, Long userId, String username, MovementType type) {
        this.id = id;
        this.timestamp = timestamp;
        this.productId = productId;
        this.productName = productName;
        this.userId = userId;
        this.username = username;
        this.type = type;
    }

    public InventoryMovementResponseDTO(Integer month, MovementType type, Long totalQuantity) {
        this.timestamp = LocalDateTime.of(2025, month, 1, 0, 0);
        this.type = type;
        this.totalQuantity = totalQuantity;
    }
}
