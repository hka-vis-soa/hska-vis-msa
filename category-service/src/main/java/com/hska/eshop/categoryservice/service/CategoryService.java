package com.hska.eshop.categoryservice.service;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Optional<Category> createCategory(String name) {
		return name != null ? Optional.of(categoryRepository.save(new Category(name))) : Optional.empty();
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	@Transactional
	public Long deleteCategoryById(Long id) {
		return categoryRepository.deleteCategoryById(id);
	}
}
