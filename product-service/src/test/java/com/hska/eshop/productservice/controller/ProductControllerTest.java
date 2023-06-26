package com.hska.eshop.productservice.controller;

import com.hska.eshop.productservice.model.Product;
import com.hska.eshop.productservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

    private ProductController productController;

    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product2;
    private List<Product> products;

    @Before
    public void setup() throws UnknownHostException {
        productController = new ProductController(productService); // Instantiate the ProductController
        product1 = new Product();
        product2 = new Product();
        product1.setName("Product 1");
        product2.setName("Product 2");
        products = Arrays.asList(product1, product2);
    }

    @Test
    public void testGetAllProducts_Success() {
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testGetProductById_ExistingId_Success() {
        Long productId = 1L;
        Mockito.when(productService.getProductById(productId)).thenReturn(Optional.of(product1));

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
    }

    @Test
    public void testGetProductById_NonExistingId_NotFound() {
        Long productId = 3L;
        Mockito.when(productService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateProduct_Success() {
        Product newProduct = new Product();
        newProduct.setName("New Product");
        Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(Optional.of(newProduct));

        ResponseEntity<Product> response = productController.createProduct(newProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newProduct, response.getBody());
    }


    @Test
    public void testDeleteProductById_ExistingId_Success() {
        Long productId = 1L;
        Mockito.when(productService.deleteProductById(productId)).thenReturn(Optional.of(productId));
        Mockito.when(productService.getAllProducts()).thenReturn(Collections.singletonList(product2));

        ResponseEntity<List<Product>> response = productController.deleteProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(product2), response.getBody());
    }

    @Test
    public void testDeleteProductById_NonExistingId_Conflict() {
        Long productId = 3L;
        Mockito.when(productService.deleteProductById(productId)).thenReturn(Optional.empty());
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.deleteProductById(productId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}
