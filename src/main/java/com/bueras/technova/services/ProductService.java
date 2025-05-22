package com.bueras.technova.services;

import com.bueras.technova.models.Product;
import com.bueras.technova.models.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(Product product);
    ProductDTO updateProduct(Long id, Product product);
    List<ProductDTO> getByCategoryId(Long categoryId);
    void deleteProduct(Long id);
}
