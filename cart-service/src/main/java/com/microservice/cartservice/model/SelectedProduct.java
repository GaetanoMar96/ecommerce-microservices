package com.microservice.cartservice.model;

public record SelectedProduct(
        String name,
        String description,
        Double price,
        String image
) {
}
