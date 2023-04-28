package com.hska.eshop.productservice.service;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Optional<Product> createProduct(String name, Double price, String description, Long category_id) {
        return name != null ? Optional.of(productRepository.save(new Product(name, price, description, category_id))) : Optional.empty();
    }

}
