package com.microservice.inventoryservice.model;

public record Product
        (String id,
         String name,
         String category,
         String description,
         Double price,
         String imageUrl) {

}

