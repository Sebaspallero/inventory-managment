package com.sebastian.inventory_management.DTO.Order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sebastian.inventory_management.DTO.OrderItem.OrderItemResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    
    private Long id;
    private String orderNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    private Long supplierId;
    private String supplierName;

    private List<OrderItemResponseDTO> items;
}
