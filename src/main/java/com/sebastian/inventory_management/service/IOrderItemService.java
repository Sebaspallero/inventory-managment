package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.OrderItem;

public interface IOrderItemService {
    OrderItem saveOrderItem(OrderItem item);
    void deleteOrderItem(Long id);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
    Integer getTotalQuantityOrdered(Long productId);
}
