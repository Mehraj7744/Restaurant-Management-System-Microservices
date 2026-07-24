package com.restaurant.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class RestaurantServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceRegistryApplication.class, args);
		System.err.println("Eureka Running");
	}

}
