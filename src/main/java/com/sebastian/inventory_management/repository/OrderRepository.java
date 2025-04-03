package com.sebastian.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.inventory_management.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    

}
