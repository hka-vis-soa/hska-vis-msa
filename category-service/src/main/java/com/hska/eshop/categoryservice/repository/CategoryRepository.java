package com.hska.eshop.categoryservice.repository;

import com.hska.eshop.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Long deleteCategoryById(Long id);
}
