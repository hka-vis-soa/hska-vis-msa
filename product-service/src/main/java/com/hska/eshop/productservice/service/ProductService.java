package com.hska.eshop.productservice.service;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.repository.ProductRepository;
import com.hska.eshop.productservice.webclient.RequestClient;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final RequestClient requestClient;

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, RequestClient requestClient) {
        this.productRepository = productRepository;
        this.requestClient = requestClient;
    }

    public Optional<Product> createProduct(Product product) {
        Optional<Product> result = Optional.empty();
        try {
            if(requestClient.doesCategoryExist(product.getCategory_id())) {
                result = Optional.of(productRepository.save(product));
            }
        } catch(Exception e) {
            logger.error("Connection to category service couldn't be established!");
        }

        return result;
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
