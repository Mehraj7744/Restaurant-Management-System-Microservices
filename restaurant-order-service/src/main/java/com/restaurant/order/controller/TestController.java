package com.restaurant.order.controller;

import org.springframework.web.bind.annotation.*;

import com.restaurant.order.client.MenuServiceClient;
import com.restaurant.order.client.dto.MenuItemResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final MenuServiceClient menuServiceClient;

    @GetMapping("/{id}")
    public MenuItemResponse test(@PathVariable Long id) {

        return menuServiceClient.getMenuItemById(id);

    }

}