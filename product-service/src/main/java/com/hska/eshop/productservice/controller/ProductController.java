package com.hska.eshop.productservice.controller;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductController {

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Product> createCategory(@RequestBody CreateProductRequest request) {
        logger.info("Add new product: " + request.getName() + " with category_id: " + request.getCategory_id());
        Optional<Product> optProduct = productService.createProduct(request.getName(), request.getPrice(), request.getDescription(), request.getCategory_id());
        return optProduct
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }


}