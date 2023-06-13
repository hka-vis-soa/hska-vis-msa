package com.hska.eshop.categoryservice.controller;

import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    private Category category1;
    private Category category2;
    private List<Category> categories;

    @Before
    public void setup() throws UnknownHostException {
        categoryController = new CategoryController(categoryService); // Instantiate the CategoryController
        category1 = new Category("Category 1");
        category2 = new Category("Category 2");
        categories = Arrays.asList(category1, category2);
    }

    @Test
    public void testGetAllCategories_Success() {
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testGetCategoryById_ExistingId_Success() {
        Long categoryId = 1L;
        Mockito.when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.of(category1));

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category1, response.getBody());
    }

    @Test
    public void testGetCategoryById_NonExistingId_NotFound() {
        Long categoryId = 3L;
        Mockito.when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateCategory_Success() {
        Category newCategory = new Category("New Category");
        Mockito.when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(Optional.of(newCategory));

        ResponseEntity<Category> response = categoryController.createCategory(newCategory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newCategory, response.getBody());
    }


    @Test
    public void testDeleteCategoryById_ExistingId_Success() {
        Long categoryId = 1L;
        Mockito.when(categoryService.deleteCategoryById(categoryId)).thenReturn(Optional.of(categoryId));
        Mockito.when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(category2));

        ResponseEntity<List<Category>> response = categoryController.deleteCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(category2), response.getBody());
    }

    @Test
    public void testDeleteCategoryById_NonExistingId_Conflict() {
        Long categoryId = 3L;
        Mockito.when(categoryService.deleteCategoryById(categoryId)).thenReturn(Optional.empty());
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.deleteCategoryById(categoryId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }
}
