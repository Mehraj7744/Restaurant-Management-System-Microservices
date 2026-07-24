package com.restaurant.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI restaurantOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant Management System - Order Service API")
                        .description("REST APIs for Order Management Microservice")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mehraj Pathan")
                                .email("mehrajpathan7744@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")));
    }
}