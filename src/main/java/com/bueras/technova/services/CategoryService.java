package com.bueras.technova.services;

import com.bueras.technova.models.Category;
import com.bueras.technova.models.Product;
import com.bueras.technova.models.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category updated);
    void deleteCategory(Long id);

    CategoryDTO getCategoryDTOById(Long id);
}
