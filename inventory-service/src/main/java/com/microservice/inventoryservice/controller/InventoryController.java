package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.model.Product;
import com.microservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/save/{inventoryCategory}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveProduct(@RequestBody Product product, @PathVariable String inventoryCategory) {
        inventoryService.saveProduct(inventoryCategory, product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
