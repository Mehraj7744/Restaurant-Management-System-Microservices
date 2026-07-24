package com.restaurant.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.menu.dto.request.CategoryRequest;
import com.restaurant.menu.dto.response.CategoryResponse;
import com.restaurant.menu.entity.Category;
import com.restaurant.menu.exception.CategoryNotFoundException;
import com.restaurant.menu.repository.CategoryRepository;
import com.restaurant.menu.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryResponse create(CategoryRequest request) {

        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Category already exists.");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        category = repository.save(category);

        return map(category);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        return map(category);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        category = repository.save(category);

        return map(category);
    }

    @Override
    public void delete(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        repository.delete(category);
    }

    private CategoryResponse map(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.getActive())
                .build();
    }
}