package com.hska.eshop.categoryservice.controller;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.service.CategoryService;
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

	@PostMapping(path = "/")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		logger.info("Add new category: " + category);
		Optional<Category> optCategory = categoryService.createCategory(category);
		return optCategory
				.map(createdcategory -> new ResponseEntity<>(createdcategory, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
	}
	@GetMapping(path = "/")
	public List<Category> getAllCategories() {
		logger.info("Received getAllCategories request");
		return categoryService.getAllCategories();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable Long id) {
		logger.info("Received deleteCategoryById request with id: " + id);
		Long deletedId = categoryService.deleteCategoryById(id);
		logger.info("Deleted category with id: " + deletedId);
		List<Category> categories = categoryService.getAllCategories();
		return Objects.equals(deletedId, id)
				? new ResponseEntity<>(categories, HttpStatus.OK)
				: new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
	}
}
