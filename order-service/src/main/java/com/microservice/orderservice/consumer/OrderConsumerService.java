package com.microservice.orderservice.consumer;

import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumerService {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consume(Order order) {
        orderRepository.save(order);
    }
}
