package com.hska.eshop.productservice.controller;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductController {

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Add new product: " + product);
        Optional<Product> optProduct = productService.createProduct(product);
        return optProduct
                .map(createdProduct -> new ResponseEntity<>(createdProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("Received getAllProducts request");
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Received getProductById request with id: " + id);
        Optional<Product> product = productService.getProductById(id);
        return product.map(byId -> new ResponseEntity<>(byId, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(path = "/search")
    public List<Product> getProductsForSearchValues(
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice)
    {
        logger.info("Received getProductsForSearchValues request with description: " + description + " minPrice " + minPrice + " maxPrice " + maxPrice);
        return productService.getProductsForSearchValues(description, minPrice, maxPrice);
    }

    @GetMapping(path = "/category/{id}")
    public List<Product> getProductsByCategoryId(@PathVariable Long id) {
        logger.info("Received getProductsByCategoryId request");
        return productService.getProductsByCategoryId(id);
    }

    @GetMapping(path = "/category/{id}/verify")
    public boolean hasNoReferencedProducts(@PathVariable Long id) {
        logger.info("Received verifyProductsForCategory request with id: " + id);
        return productService.getProductsByCategoryId(id).isEmpty();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<List<Product>> deleteProductById(@PathVariable Long id) {
        logger.info("Received deleteProductById request with id: " + id);
        Optional<Long> deletedId = productService.deleteProductById(id);
        List<Product> products = productService.getAllProducts();
        return deletedId
                .map(delId -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(products, HttpStatus.CONFLICT));
    }


}
