package com.bueras.technova.services.impl;

import com.bueras.technova.models.Product;
import com.bueras.technova.models.dto.ProductDTO;
import com.bueras.technova.repositories.ProductRepository;
import com.bueras.technova.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductDTO getProductById(Long id) {
        return toDTO(productRepository.findById(id).orElse(null));
    }

    public ProductDTO createProduct(Product product) {
        System.out.println(product.getCategory());;
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        Product p = productRepository.save(product);
        return toDTO(product);
    }

    public ProductDTO updateProduct(Long id, Product updated) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }
        Product product = optionalProduct.get();
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        updated = productRepository.save(product);
        return toDTO(updated);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::toDTO)
                .toList();
    }

    private ProductDTO toDTO(Product product) {
        return product == null ? null : new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory() != null ? product.getCategory().getName() : null
        );
    }
}