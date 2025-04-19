package com.sebastian.inventory_management.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebastian.inventory_management.DTO.Order.OrderCountByMonthDTO;
import com.sebastian.inventory_management.DTO.Order.OrderResponseDTO;
import com.sebastian.inventory_management.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

        List<Order> findBySupplierId(Long supplierId);

        Optional<Order> findByOrderNumber(String orderNumber);

        @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
        List<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

        @Query("SELECT new com.sebastian.inventory_management.DTO.Order.OrderCountByMonthDTO(" +
                        "COUNT(o.id), " +
                        "DATE(CONCAT(CAST(YEAR(MIN(o.orderDate)) AS string), '-', LPAD(CAST(MONTH(MIN(o.orderDate)) AS string), 2, '0'), '-01'))) "
                        +
                        "FROM Order o " +
                        "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) " +
                        "ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
        OrderCountByMonthDTO countOrdersByMonth();

        @Query("SELECT new com.sebastian.inventory_management.DTO.Order.OrderResponseDTO(o.id, o.orderNumber, o.orderDate, s.id, s.name) "
                        +
                        "FROM Order o JOIN o.supplier s ORDER BY o.orderDate DESC")
        List<OrderResponseDTO> findRecentOrders();

}
