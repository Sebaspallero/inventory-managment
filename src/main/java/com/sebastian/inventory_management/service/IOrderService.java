package com.sebastian.inventory_management.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sebastian.inventory_management.DTO.Order.OrderCountByMonthDTO;
import com.sebastian.inventory_management.DTO.Order.OrderRequestDTO;
import com.sebastian.inventory_management.DTO.Order.OrderResponseDTO;
import com.sebastian.inventory_management.model.Order;

public interface IOrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderDTO);
    OrderResponseDTO getOrderById(Long id);
    OrderResponseDTO getOrderByOrderNumber(String orderNumber);
    List<OrderResponseDTO> getAllOrders();
    List<OrderResponseDTO> getOrdersBySupplier(Long supplierId);
    List<OrderResponseDTO> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderDTO);
    OrderCountByMonthDTO countOrdersByMonth();
    List<OrderResponseDTO> findRecentOrders();
    void deleteOrder(Long id);
    Order getOrderByIdEntity(Long id);
}
