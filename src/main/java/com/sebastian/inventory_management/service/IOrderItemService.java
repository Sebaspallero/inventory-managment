package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.OrderItem;

public interface IOrderItemService {
    OrderItem saveOrderItem(OrderItem item);
    void deleteOrderItem(Long id);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
}
