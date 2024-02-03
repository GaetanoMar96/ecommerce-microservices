package com.microservice.productservice.service;

import com.microservice.productservice.exception.MongoDbException;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        try {
            return productRepository.findAllByCategory(category);
        } catch(Exception e) {
            throw new MongoDbException();
        }
    }

    public Optional<Product> getProductsById(String productId) {
        try {
            return productRepository.findById(productId);
        } catch(Exception e) {
            throw new MongoDbException();
        }
    }
}
