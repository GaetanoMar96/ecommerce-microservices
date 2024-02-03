package com.microservice.productservice;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(value = "com.microservices.product-service")
public class BaseTest {


}
