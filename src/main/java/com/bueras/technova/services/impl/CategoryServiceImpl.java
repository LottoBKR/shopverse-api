package com.bueras.technova.services.impl;

import com.bueras.technova.models.Category;
import com.bueras.technova.models.Product;
import com.bueras.technova.models.dto.CategoryDTO;
import com.bueras.technova.repositories.CategoryRepository;
import com.bueras.technova.services.CategoryService;
import com.bueras.technova.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(this::toDto).toList();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category){
        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre de la caregoría no puede estar vacío.");
        }
        if (category.getDescription() == null || category.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción de la caregoría no puede estar vacío.");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updated) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("La categoría con ID " + id + " no existe.");
        }
        Category category = optionalCategory.get();
        category.setName(updated.getName());
        category.setDescription(updated.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getProducts()
        );
    }

    private CategoryDTO toDto(Category category){
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription(), category.getProducts());
    }
}
