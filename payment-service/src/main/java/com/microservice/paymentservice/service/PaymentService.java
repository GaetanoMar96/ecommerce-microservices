package com.microservice.paymentservice.service;

import com.microservice.paymentservice.model.OrderRequest;
import com.microservice.paymentservice.producer.OrderProducerService;
import com.stripe.Stripe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    private final RestTemplate  restTemplate = new RestTemplate();

    private final OrderProducerService orderProducerService;

    public ResponseEntity<String> processPayment(OrderRequest orderRequest) {
        Stripe.apiKey = secretKey;

        MultiValueMap<String, String> formData = getFormData(orderRequest.getPayload());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);

        String apiUrl = "https://api.stripe.com/v1";
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl,
                HttpMethod.POST,
                new HttpEntity<>(formData, headers),
                String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            orderProducerService.produceOrder(orderRequest.getOrders());
        }
        return responseEntity;
    }

    private MultiValueMap<String, String> getFormData(Map<String, String> payload) {
        String paymentMethodId = payload.get("paymentMethodId");
        String amount = payload.get("amount");
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("paymentMethodId", paymentMethodId);
        formData.add("amount", amount);
        formData.add("currency", "eur");
        return formData;
    }
}
