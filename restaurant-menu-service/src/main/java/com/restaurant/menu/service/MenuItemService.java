package com.restaurant.menu.service;

import java.util.List;

import com.restaurant.menu.dto.request.MenuItemRequest;
import com.restaurant.menu.dto.response.MenuItemResponse;

public interface MenuItemService {

    MenuItemResponse create(MenuItemRequest request);

    MenuItemResponse update(Long id, MenuItemRequest request);

    MenuItemResponse getById(Long id);

    List<MenuItemResponse> getAll();

    List<MenuItemResponse> getByCategory(Long categoryId);

    void delete(Long id);

    MenuItemResponse changeAvailability(Long id, Boolean available);
}