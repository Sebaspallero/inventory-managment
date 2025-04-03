package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
