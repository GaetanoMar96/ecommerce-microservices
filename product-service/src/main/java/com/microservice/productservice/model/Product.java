package com.microservice.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product")
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;

    private String category;

    private String description;

    private Double price;

    private String imageUrl;
}
