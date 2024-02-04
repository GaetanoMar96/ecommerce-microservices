package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.model.Product;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void saveProduct(String inventoryCategory, Product product) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findInventoryByCategory(inventoryCategory);
        final Inventory inventory;
        if (inventoryOpt.isPresent()) {
            inventory = inventoryOpt.get();
            inventory.getProducts().add(product);
            inventoryRepository.save(inventory);
        } else {
            inventory = new Inventory();
            inventory.setInventoryCategory(inventoryCategory);
            inventory.setProducts(Collections.singletonList(product));
            inventoryRepository.save(inventory);
        }
    }
}
