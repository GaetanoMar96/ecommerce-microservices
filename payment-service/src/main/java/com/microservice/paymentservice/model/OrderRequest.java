package com.microservice.paymentservice.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderRequest {

    private Map<String, String> payload;

    private List<Order> orders;

    @Data
    public static class Order {
        private String orderId;
    }

}
