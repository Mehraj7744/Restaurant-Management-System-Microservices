package com.restaurant.order.client.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MenuItemResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean available;

    private Integer preparationTime;

    private String imageUrl;

    private Long categoryId;
}