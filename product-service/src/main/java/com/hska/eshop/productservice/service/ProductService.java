package com.hska.eshop.productservice.service;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Optional<Product> createProduct(String name, Double price, String description, Long category_id) {
        if (name == null || price == null || description == null || category_id == null) {
            return Optional.empty();
        } else {
            return Optional.of(productRepository.save(new Product(name, price, description, category_id)));
        }
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    //getAllProductsByCategory

    @Transactional
    public Long deleteProductById(Long id) {
        return productRepository.deleteProductById(id);
    }



}
