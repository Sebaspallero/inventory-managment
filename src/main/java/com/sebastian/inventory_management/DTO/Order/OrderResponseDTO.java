package com.sebastian.inventory_management.DTO.Order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sebastian.inventory_management.DTO.OrderItem.OrderItemResponseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDTO {
    
    private Long id;
    private String orderNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    private Long supplierId;
    private String supplierName;

    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO(Long id, String orderNumber, LocalDateTime orderDate, Long supplierId, String supplierName,
            List<OrderItemResponseDTO> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.items = items;
    }
    
    public OrderResponseDTO(Long id, String orderNumber, LocalDateTime orderDate, Long supplierId, String supplierName) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public OrderResponseDTO(Long id, LocalDateTime orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }
}
