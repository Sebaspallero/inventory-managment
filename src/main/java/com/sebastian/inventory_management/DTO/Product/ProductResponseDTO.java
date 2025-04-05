package com.sebastian.inventory_management.DTO.Product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    
    private Long id;
    private String name;
    private String description;
    private int stock;
    private BigDecimal price;
    private String categoryName;
    private String supplierName;
}
