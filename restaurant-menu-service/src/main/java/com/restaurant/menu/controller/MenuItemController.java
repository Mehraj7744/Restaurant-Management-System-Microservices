package com.restaurant.menu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.restaurant.menu.dto.request.MenuItemRequest;
import com.restaurant.menu.dto.response.MenuItemResponse;
import com.restaurant.menu.service.MenuItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponse create(@Valid @RequestBody MenuItemRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<MenuItemResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<MenuItemResponse> getByCategory(@PathVariable Long categoryId) {
        return service.getByCategory(categoryId);
    }

    @PutMapping("/{id}")
    public MenuItemResponse update(@PathVariable Long id,
                                   @Valid @RequestBody MenuItemRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/availability")
    public MenuItemResponse changeAvailability(
            @PathVariable Long id,
            @RequestParam Boolean available) {

        return service.changeAvailability(id, available);
    }
}