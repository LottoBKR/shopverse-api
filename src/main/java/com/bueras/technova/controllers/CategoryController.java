package com.bueras.technova.controllers;

import com.bueras.technova.models.Category;
import com.bueras.technova.models.dto.CategoryDTO;
import com.bueras.technova.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> products = categoryService.getAllCategories();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(products); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        CategoryDTO c = categoryService.getCategoryById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody Category category) {
        try {
            CategoryDTO created = categoryService.createCategory(category);
            return ResponseEntity.ok(created); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request si no se logra crear
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,@Valid @RequestBody Category category) {
        try {
            CategoryDTO updated = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found

        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CategoryDTO> getCategoryDetails(@PathVariable Long id) {
        try {
            CategoryDTO dto = categoryService.getCategoryDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}