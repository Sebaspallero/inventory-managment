package com.sebastian.inventory_management.DTO.Product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;
    
    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;
    
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount (max 2 decimal places)")
    private BigDecimal price;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
    
    @NotNull(message = "Supplier ID is required")
    private Long supplierId;
}
