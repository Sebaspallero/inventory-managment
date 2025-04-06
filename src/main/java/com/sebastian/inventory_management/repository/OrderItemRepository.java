package com.sebastian.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sebastian.inventory_management.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
 
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
}
