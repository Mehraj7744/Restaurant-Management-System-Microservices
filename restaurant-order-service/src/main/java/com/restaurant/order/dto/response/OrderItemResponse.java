package com.restaurant.order.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    private Long id;

    private Long menuItemId;

    private String menuItemName;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal subtotal;
}