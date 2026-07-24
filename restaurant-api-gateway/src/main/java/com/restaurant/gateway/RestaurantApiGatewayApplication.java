package com.restaurant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApiGatewayApplication.class, args);
		System.err.println("Api Gateway Running");

	}

}
