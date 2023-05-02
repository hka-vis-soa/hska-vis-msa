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
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "category_id", nullable = false)
    private Long category_id;

    /**
     * Don't delete hibernate empty constructor
     */
    public Product() {}

    public Product(String name, Double price, String description, Long category_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Long category_id) {
        this.category_id = category_id;
    }
}
