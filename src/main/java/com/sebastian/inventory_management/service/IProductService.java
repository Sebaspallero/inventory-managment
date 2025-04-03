package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.Product;

public interface IProductService {
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
