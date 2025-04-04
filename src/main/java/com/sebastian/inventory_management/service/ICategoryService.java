package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.Category;

public interface ICategoryService {
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Category getCategoryByName(String name);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}
