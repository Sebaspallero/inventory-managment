package com.sebastian.inventory_management.DTO.Order;

import java.util.List;

import com.sebastian.inventory_management.DTO.OrderItem.OrderItemRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<@Valid OrderItemRequestDTO> items;

}
