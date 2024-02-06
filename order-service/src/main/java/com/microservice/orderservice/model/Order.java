package com.microservice.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("order")
@AllArgsConstructor
public class Order {

    private String orderId;
}
