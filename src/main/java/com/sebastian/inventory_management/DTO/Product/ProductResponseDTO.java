package com.sebastian.inventory_management.DTO.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private int stock;
    private BigDecimal price;
    private String categoryName;
    private String supplierName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductResponseDTO(Long id, String name, String description, int stock, BigDecimal price,
            String categoryName, String supplierName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
    }

    public ProductResponseDTO(String categoryName, Integer value) {
        this.categoryName = categoryName;
        this.stock = value;
    }

}
