package com.hska.eshop.productservice.service;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Optional<Product> createProduct(Product product) {
        return Optional.of(productRepository.save(product));
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryId(Long category_id) {
        return productRepository.findByCategoryId(category_id);
    }

    @Transactional
    public Optional<Long> deleteProductById(Long id) {
        return Optional.of(productRepository.deleteProductById(id));
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsForSearchValues(String details, Double minPrice, Double maxPrice) {
        return productRepository.findProductsForSearchValues(details, minPrice, maxPrice);
    }
}
