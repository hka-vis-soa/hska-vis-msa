package com.hska.eshop.categoryservice.service;

import com.hska.eshop.categoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public String getHello() {
		return categoryRepository.findHello();
	}
}
