package com.microservice.inventoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.inventoryservice.exception.MongoDbException;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.model.Product;
import com.microservice.inventoryservice.repository.InventoryRepository;
import com.microservice.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SpyBean
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository inventoryRepository;

    @Test
    public void saveProductOk() throws Exception {
        Product product = new Product("productId", "Footwear", "Running shoes", "descr", 100.0, "image.jpg");
        Inventory inventory = new Inventory();
        inventory.setId("inventoryId");
        inventory.setInventoryCategory("category");
        List<Product> products = new ArrayList<>();
        products.add(product);
        inventory.setProducts(products);
        when(inventoryRepository.findInventoryByCategory("category")).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        mockMvc.perform(post("/api/v1/inventory/save/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveProductOkNoInventory() throws Exception {
        Product product = new Product("productId", "Footwear", "Running shoes", "descr", 100.0, "image.jpg");
        Inventory inventory = new Inventory();
        inventory.setId("inventoryId");
        inventory.setInventoryCategory("category");
        inventory.setProducts(Collections.singletonList(product));
        when(inventoryRepository.findInventoryByCategory("category")).thenReturn(Optional.empty());
        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        mockMvc.perform(post("/api/v1/inventory/save/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveProductKo() throws Exception {
        Product product = new Product("productId", "Footwear", "Running shoes", "descr", 100.0, "image.jpg");
        Inventory inventory = new Inventory();
        inventory.setId("inventoryId");
        inventory.setInventoryCategory("category");
        List<Product> products = new ArrayList<>();
        products.add(product);
        inventory.setProducts(products);
        when(inventoryRepository.findInventoryByCategory("category")).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(inventory)).thenThrow(MongoDbException.class);

        mockMvc.perform(post("/api/v1/inventory/save/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError());
    }
}
