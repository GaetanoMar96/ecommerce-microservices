package com.microservice.productservice.controller;

import com.microservice.productservice.model.Product;
import com.microservice.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProducts() throws Exception {
        List<Product> products = List.of(new Product("objectId", "Footwear", "Shoes", "Nike","Running shoes", 100.0, Collections.singletonList(new Product.Image())));
        when(productService.getProducts()).thenReturn(products);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("objectId")));
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        List<Product> products = List.of(new Product("objectId", "Footwear", "Shoes", "Nike", "Running shoes", 100.0, Collections.singletonList(new Product.Image())));
        when(productService.getProductsByCategory("Shoes")).thenReturn(products);

        mockMvc.perform(get("/api/v1/products/Shoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("Shoes")));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product("objectId", "Footwear", "Shoes", "Nike","Running shoes", 100.0, Collections.singletonList(new Product.Image()));
        when(productService.getProductsById("objectId")).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/v1/products/id/objectId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("objectId")));
    }
}
