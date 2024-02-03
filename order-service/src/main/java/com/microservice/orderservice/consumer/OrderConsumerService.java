package com.microservice.orderservice.consumer;

import com.microservice.orderservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consume(Order order) {
        System.out.println("Consumed order: " + order);
    }
}
