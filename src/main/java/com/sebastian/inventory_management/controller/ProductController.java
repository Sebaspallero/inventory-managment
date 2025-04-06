package com.sebastian.inventory_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.inventory_management.DTO.Product.ProductRequestDTO;
import com.sebastian.inventory_management.DTO.Product.ProductResponseDTO;
import com.sebastian.inventory_management.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
        ProductResponseDTO createdProduct = productService.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name) {
        ProductResponseDTO product = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductResponseDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsBySupplierId(@PathVariable Long supplierId) {
        List<ProductResponseDTO> products = productService.getProductBySupplierId(supplierId);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponseDTO>> searchProductsByName(@PathVariable String name) {
        List<ProductResponseDTO> products = productService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/stock/less/{stockThreshold}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByStockLessThan(@PathVariable int stockThreshold) {
        List<ProductResponseDTO> products = productService.findByStockLessThan(stockThreshold);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/stock/more/{stockThreshold}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByStockMoreThan(@PathVariable int stockThreshold) {
        List<ProductResponseDTO> products = productService.findByStockMoreThan(stockThreshold);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequest) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
