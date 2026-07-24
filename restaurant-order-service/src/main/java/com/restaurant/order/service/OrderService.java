package com.restaurant.order.service;

import java.util.List;

import com.restaurant.order.dto.request.CreateOrderRequest;
import com.restaurant.order.dto.response.OrderResponse;
import com.restaurant.order.enums.OrderStatus;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getOrdersByStatus(OrderStatus status);

    OrderResponse updateStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);

}