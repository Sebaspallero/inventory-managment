package com.sebastian.inventory_management.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sebastian.inventory_management.model.Order;

public interface IOrderService {
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> findBySupplierId(Long supplierId);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
