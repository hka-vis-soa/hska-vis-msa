package com.hska.eshop.categoryservice.service;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.repository.CategoryRepository;
import com.hska.eshop.categoryservice.webclient.RequestClient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final RequestClient requestClient;

	public CategoryService(CategoryRepository categoryRepository, RequestClient requestClient) {
		this.categoryRepository = categoryRepository;
		this.requestClient = requestClient;
	}

	public Optional<Category> createCategory(Category category) {
		return Optional.of(categoryRepository.save(category));
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	@Transactional
	public Optional<Long> deleteCategoryById(Long id) {
		Optional<Long> deletedId = Optional.empty();
		if(this.requestClient.hasNoReferencedProducts(id)) {
			deletedId = Optional.of(categoryRepository.deleteCategoryById(id));
		}
		return deletedId;
	}
}
