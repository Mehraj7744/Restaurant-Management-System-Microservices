package com.restaurant.menu.service;

import java.util.List;

import com.restaurant.menu.dto.request.CategoryRequest;
import com.restaurant.menu.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

}