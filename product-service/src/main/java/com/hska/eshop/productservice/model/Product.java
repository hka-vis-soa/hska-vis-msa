package com.hska.eshop.productservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products", schema="productservice")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "category_id", nullable = false)
    private Long category_id;

    /**
     * Don't delete hibernate empty constructor
     */
    public Product() {}

    public Product(String name, Double price, String details, Long category_id) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.category_id = category_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
