package com.hska.eshop.categoryservice.repository;

import com.hska.eshop.categoryservice.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	default String findHello() {
		return "hello from category-service";
	}
}
