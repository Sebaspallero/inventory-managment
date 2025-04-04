package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.Product;

public interface IProductService {
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    Product getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> getProductBySupplierId(Long supplierId);
    List<Product> findByStockLessThan(int stockThreshold);
    List<Product> findByStockMoreThan(int stockThreshold);
}
