package com.microservice.cartservice.service;

import com.microservice.cartservice.model.SelectedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, SelectedProduct> redisTemplate;

    public void addToCart(String userId, SelectedProduct selectedProduct){
        redisTemplate.opsForList().rightPush(userId, selectedProduct);
    }

    public void removeFromCart(String userId, SelectedProduct selectedProduct){
        redisTemplate.opsForList().remove(userId, 0, selectedProduct);
    }

    public List<SelectedProduct> getSelectedProducts(String userId){
        return redisTemplate.opsForList().range(userId, 0, -1);
    }
}
