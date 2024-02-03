package com.microservice.cartservice.controller;

import com.microservice.cartservice.model.SelectedProduct;
import com.microservice.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addToCart(@PathVariable String userId, @RequestBody SelectedProduct selectedProduct) {
        cartService.addToCart(userId, selectedProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable String userId, @RequestBody SelectedProduct selectedProduct) {
        cartService.removeFromCart(userId, selectedProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SelectedProduct>> getSelectedProducts(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getSelectedProducts(userId));
    }
}
