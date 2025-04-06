package com.sebastian.inventory_management.DTO.OrderItem;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {
    
    @NotNull(message = "Quantity cannot be null")
    @Min(1)
    private int quantity;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Product cannot be null")
    private Long productId;
}
