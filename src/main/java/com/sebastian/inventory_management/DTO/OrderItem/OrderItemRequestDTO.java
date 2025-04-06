package com.sebastian.inventory_management.DTO.OrderItem;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount (max 2 decimal places)")
    private BigDecimal price;

    @NotNull(message = "Product ID is required")
    private Long productId;
}
