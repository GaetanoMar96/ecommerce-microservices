package com.microservice.productservice.service;

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

    public String saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductsById(String productId) {
        return productRepository.findById(productId);
    }
}
