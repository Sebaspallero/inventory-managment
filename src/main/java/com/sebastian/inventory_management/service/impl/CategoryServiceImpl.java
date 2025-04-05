package com.sebastian.inventory_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.inventory_management.DTO.Category.CategoryRequestDTO;
import com.sebastian.inventory_management.DTO.Category.CategoryResponseDTO;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.CategoryMapper;
import com.sebastian.inventory_management.model.Category;
import com.sebastian.inventory_management.repository.CategoryRepository;
import com.sebastian.inventory_management.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService   {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryResponseDTO saveCategory(CategoryRequestDTO category) {
        validateUniqueCategoryName(category.getName(), null);
        Category categoryToSave = categoryMapper.toEntity(category);
        Category savedCategory = categoryRepository.save(categoryToSave);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Long id) {
          Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + name));
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDTOList(categories);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = getCategoryByIdEntity(id);
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO category) {
        Category categoryToUpdate = getCategoryByIdEntity(id);
        validateUniqueCategoryName(category.getName(), id);
        categoryMapper.updateEntityFromDto(category, categoryToUpdate);
        categoryRepository.save(categoryToUpdate);
        return categoryMapper.toDTO(categoryToUpdate);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
                        
    }

    @Override
    public Category getCategoryByIdEntity(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    private void validateUniqueCategoryName(String name, Long excludeId) {
        categoryRepository.findByName(name).ifPresent(existing -> {
            if (excludeId == null || !existing.getId().equals(excludeId)) {
                throw new IllegalArgumentException("Category with name '" + name + "' already exists.");
            }
        });
    }
}
