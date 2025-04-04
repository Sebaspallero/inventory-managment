package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.DTO.Category.CategoryRequestDTO;
import com.sebastian.inventory_management.DTO.Category.CategoryResponseDTO;

public interface ICategoryService {
    CategoryResponseDTO saveCategory(CategoryRequestDTO category);
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO category);
    void deleteCategory(Long id);
    CategoryResponseDTO getCategoryByName(String name);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();
}
