package com.hska.eshop.categoryservice.controller;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.service.CategoryService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		logger.info("Add new category: " + category);
		Optional<Category> optCategory = categoryService.createCategory(category);
		return optCategory
				.map(createdCategory -> new ResponseEntity<>(createdCategory, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
	}


	@GetMapping
	public List<Category> getAllCategories() {
		logger.info("Received getAllCategories request");
		return categoryService.getAllCategories();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		logger.info("Received getCategoryById request with id: " + id);
		Optional<Category> category = categoryService.getCategoryById(id);
		return category
				.map(byId -> new ResponseEntity<>(byId, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable Long id) {
		logger.info("Received deleteCategoryById request with id: " + id);
		Optional<Long> deletedId = categoryService.deleteCategoryById(id);
		List<Category> categories = categoryService.getAllCategories();
		return deletedId
				.map(delId -> new ResponseEntity<>(categories, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(categories, HttpStatus.CONFLICT));
	}
}
