package com.hska.eshop.productservice.repository;

import com.hska.eshop.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category_id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
    Long deleteProductById(Long id);
}
