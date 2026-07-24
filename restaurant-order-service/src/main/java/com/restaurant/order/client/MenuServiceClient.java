package com.restaurant.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.restaurant.order.client.dto.MenuItemResponse;

@FeignClient(name = "restaurant-menu-service")
public interface MenuServiceClient {

    @GetMapping("/api/menu-items/{id}")
    MenuItemResponse getMenuItemById(@PathVariable Long id);

}