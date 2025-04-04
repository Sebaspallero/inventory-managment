package com.sebastian.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sebastian.inventory_management.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findBySupplierId(Long supplierId);
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.stock < :stockThreshold")
    List<Product> findByStockLessThan(@Param("stockThreshold") int stockThreshold);

    @Query("SELECT p FROM Product p WHERE p.stock > :stockThreshold")
    List<Product> findByStockMoreThan(@Param("stockThreshold") int stockThreshold);
}

