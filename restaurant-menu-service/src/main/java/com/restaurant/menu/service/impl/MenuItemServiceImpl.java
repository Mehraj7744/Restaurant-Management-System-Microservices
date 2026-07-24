package com.restaurant.menu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restaurant.menu.dto.request.MenuItemRequest;
import com.restaurant.menu.dto.response.MenuItemResponse;
import com.restaurant.menu.entity.Category;
import com.restaurant.menu.entity.MenuItem;
import com.restaurant.menu.exception.CategoryNotFoundException;
import com.restaurant.menu.exception.MenuItemNotFoundException;
import com.restaurant.menu.repository.CategoryRepository;
import com.restaurant.menu.repository.MenuItemRepository;
import com.restaurant.menu.service.MenuItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public MenuItemResponse create(MenuItemRequest request) {

        if (menuItemRepository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Menu item already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        MenuItem menuItem = MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .preparationTime(request.getPreparationTime())
                .available(true)
                .category(category)
                .build();

        return mapToResponse(menuItemRepository.save(menuItem));
    }

    @Override
    public MenuItemResponse update(Long id, MenuItemRequest request) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found."));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        menuItem.setName(request.getName());
        menuItem.setDescription(request.getDescription());
        menuItem.setPrice(request.getPrice());
        menuItem.setImageUrl(request.getImageUrl());
        menuItem.setPreparationTime(request.getPreparationTime());
        menuItem.setCategory(category);

        return mapToResponse(menuItemRepository.save(menuItem));
    }

    @Override
    public MenuItemResponse getById(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found."));

        return mapToResponse(menuItem);
    }

    @Override
    public List<MenuItemResponse> getAll() {

        return menuItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponse> getByCategory(Long categoryId) {

        return menuItemRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found."));

        menuItemRepository.delete(menuItem);
    }

    @Override
    public MenuItemResponse changeAvailability(Long id, Boolean available) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found."));

        menuItem.setAvailable(available);

        return mapToResponse(menuItemRepository.save(menuItem));
    }

    private MenuItemResponse mapToResponse(MenuItem menuItem) {

        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .imageUrl(menuItem.getImageUrl())
                .available(menuItem.getAvailable())
                .preparationTime(menuItem.getPreparationTime())
                .categoryId(menuItem.getCategory().getId())
                .categoryName(menuItem.getCategory().getName())
                .build();
    }
}