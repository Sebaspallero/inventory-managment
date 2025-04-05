package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.DTO.Product.ProductRequestDTO;
import com.sebastian.inventory_management.DTO.Product.ProductResponseDTO;
import com.sebastian.inventory_management.model.Product;

public interface IProductService {
    ProductResponseDTO saveProduct(ProductRequestDTO product);
    ProductResponseDTO getProductById(Long id);
    Product getProductByIdEntity(Long id);
    ProductResponseDTO getProductByName(String name);
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getProductsByCategory(Long categoryId);
    List<ProductResponseDTO> getProductBySupplierId(Long supplierId);
    List<ProductResponseDTO> findByNameContainingIgnoreCase(String name);
    List<ProductResponseDTO> findByStockLessThan(int stockThreshold);
    List<ProductResponseDTO> findByStockMoreThan(int stockThreshold);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO product);
    void deleteProduct(Long id);
}
