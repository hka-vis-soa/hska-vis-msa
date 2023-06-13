package com.hska.eshop.categoryservice.controller;

import com.hska.eshop.categoryservice.controller.CategoryController;
import com.hska.eshop.categoryservice.model.Category;
import com.hska.eshop.categoryservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    private Category category;

    /*@BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Max");
        user.setLastName("Testermann");
        user.setEmail("max@testermann.de");
        user.setMatriculationNumber("123456");
        user.setUniversity("Hochschule Reutlingen");
    }

    @Test
    void canCreateCategory() throws Exception {
        Mockito.when(userService.save(user)).thenReturn(user);
        mockMvc.perform(post("/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }*/

    @Test
    public void testGetEmptyCategories() throws Exception {
        mockMvc.perform(get("/v1/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

}