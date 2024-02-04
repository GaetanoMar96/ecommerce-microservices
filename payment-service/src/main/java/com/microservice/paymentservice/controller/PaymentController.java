package com.microservice.paymentservice.controller;

import com.microservice.paymentservice.model.OrderRequest;
import com.microservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveProduct(@RequestBody OrderRequest orderRequest) {
        return paymentService.processPayment(orderRequest);
    }
}
