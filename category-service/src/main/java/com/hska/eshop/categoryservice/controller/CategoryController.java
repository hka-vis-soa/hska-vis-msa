package com.hska.eshop.categoryservice.controller;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@RestController
@RequestMapping(path = "/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	private final HttpHeaders headers = new HttpHeaders();


	public CategoryController(CategoryService categoryService) throws UnknownHostException {
		this.categoryService = categoryService;
		headers.add("Pod-Identifier", InetAddress.getLocalHost().getHostAddress());
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		logger.info("Add new category: " + category);
		Optional<Category> optCategory = categoryService.createCategory(category);
		return optCategory
				.map(createdCategory -> new ResponseEntity<>(createdCategory, headers, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
	}


	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		logger.info("Received getAllCategories request");
		return new ResponseEntity<>(categoryService.getAllCategories(), headers, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		logger.info("Received getCategoryById request with id: " + id);
		Optional<Category> category = categoryService.getCategoryById(id);
		return category
				.map(byId -> new ResponseEntity<>(byId, headers, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/{id}/verify")
	public boolean doesCategoryExist(@PathVariable Long id) {
		logger.info("Received doesCategoryExist request with id: " + id);
		return categoryService.getCategoryById(id).isPresent();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable Long id) {
		logger.info("Received deleteCategoryById request with id: " + id);
		Optional<Long> deletedId = categoryService.deleteCategoryById(id);
		List<Category> categories = categoryService.getAllCategories();
		return deletedId
				.map(delId -> new ResponseEntity<>(categories, headers, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(categories, HttpStatus.CONFLICT));
	}
}
