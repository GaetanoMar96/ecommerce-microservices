package com.microservice.paymentservice.producer;

import com.microservice.paymentservice.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProducerService {

    private static final String TOPIC = "order-topic";

    private final KafkaTemplate<String, OrderRequest.Order> kafkaTemplate;

    public void produceOrder(List<OrderRequest.Order> orders) {
        orders.forEach(
                order -> kafkaTemplate.send(TOPIC, order.getOrderId(), order)
        );
    }
}
