package com.microservice.productservice.repository;

import com.microservice.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'category' : ?0 }")
    List<Product> findAllByCategory(String category);
}
