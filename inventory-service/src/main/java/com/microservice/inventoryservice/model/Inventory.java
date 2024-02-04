package com.microservice.inventoryservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("inventory")
public class Inventory {

    @Id
    private String id;

    private String inventoryCategory;

    private List<Product> products;
}
