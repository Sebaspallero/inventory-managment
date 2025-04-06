package com.sebastian.inventory_management.DTO.OrderItem;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {

    private Long id;
    private int quantity;
    private BigDecimal price;
    private String productName;
}
