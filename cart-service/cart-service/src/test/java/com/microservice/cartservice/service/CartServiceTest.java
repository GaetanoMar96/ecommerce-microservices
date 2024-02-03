package com.microservice.cartservice.service;

import com.microservice.cartservice.model.SelectedProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private RedisTemplate<String, SelectedProduct> redisTemplate;

    @Test
    public void testAddToCart() {
        SelectedProduct product = new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg");
        cartService.addToCart("user1", product);
        List<SelectedProduct> cart = redisTemplate.opsForList().range("user1", 0, -1);
        assertNotNull(cart);
        assertEquals(cart.get(0), product);
    }

    @Test
    public void testRemoveFromCart() {
        SelectedProduct product = new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg");

        cartService.addToCart("user1", product);
        cartService.removeFromCart("user1", product);
        List<SelectedProduct> cart = redisTemplate.opsForList().range("user1", 0, -1);
        assertNotNull(cart);
        assertTrue(cart.isEmpty());
    }

    @Test
    public void testGetSelectedProducts() {
        SelectedProduct product1 = new SelectedProduct("Footwear", "Running shoes", 100.0, "image.jpg");
        SelectedProduct product2 = new SelectedProduct("FootwearNew", "Running shoes", 100.0, "image.jpg");
        cartService.addToCart("user1", product1);
        cartService.addToCart("user2", product2);
        List<SelectedProduct> cart1 = cartService.getSelectedProducts("user1");
        List<SelectedProduct> cart2 = cartService.getSelectedProducts("user2");
        assertEquals(cart1.get(0), product1);
        assertEquals(cart2.get(0), product2);
    }
}

