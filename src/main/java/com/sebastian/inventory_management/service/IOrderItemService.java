package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.DTO.OrderItem.OrderItemResponseDTO;

public interface IOrderItemService {
    OrderItemResponseDTO getOrderItemById(Long id);
    List<OrderItemResponseDTO> findByOrderId(Long orderId);
    List<OrderItemResponseDTO> findByProductId(Long productId);
}
