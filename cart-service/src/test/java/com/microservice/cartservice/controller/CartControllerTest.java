package com.microservice.cartservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.cartservice.model.SelectedProduct;
import com.microservice.cartservice.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CartService cartService;

    @Test
    public void addToCart() throws Exception {
        SelectedProduct product = new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg");
        doNothing().when(cartService).addToCart("userId", product);

        mockMvc.perform(post("/api/v1/cart/add/userId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                        .andExpect(status().isOk());
    }

    @Test
    public void removeFromCart() throws Exception {
        SelectedProduct product = new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg");
        doNothing().when(cartService).removeFromCart("userId", product);

        mockMvc.perform(delete("/api/v1/cart/remove/userId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void getSelectedProducts() throws Exception {
        List<SelectedProduct> products = List.of(new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg"));
        when(cartService.getSelectedProducts("userId")).thenReturn(products);

        mockMvc.perform(get("/api/v1/cart/userId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Footwear")))
                .andExpect(jsonPath("$[0].description", is("Running shoes")));
    }
}
