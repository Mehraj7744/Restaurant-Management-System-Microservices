package com.restaurant.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.order.client.MenuServiceClient;
import com.restaurant.order.client.dto.MenuItemResponse;
import com.restaurant.order.dto.request.CreateOrderRequest;
import com.restaurant.order.dto.request.OrderItemRequest;
import com.restaurant.order.dto.response.OrderItemResponse;
import com.restaurant.order.dto.response.OrderResponse;
import com.restaurant.order.entity.Order;
import com.restaurant.order.entity.OrderItem;
import com.restaurant.order.enums.OrderStatus;
import com.restaurant.order.exception.MenuItemUnavailableException;
import com.restaurant.order.exception.OrderNotFoundException;
import com.restaurant.order.repository.OrderRepository;
import com.restaurant.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuServiceClient menuServiceClient;
    
    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .customerPhone(request.getCustomerPhone())
                .customerAddress(request.getCustomerAddress())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            MenuItemResponse menuItem =
                    menuServiceClient.getMenuItemById(itemRequest.getMenuItemId());

            if (!Boolean.TRUE.equals(menuItem.getAvailable())) {
                throw new MenuItemUnavailableException(
                        menuItem.getName() + " is currently unavailable.");
            }

            BigDecimal subtotal =
                    menuItem.getPrice()
                            .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItem.getId())
                    .menuItemName(menuItem.getName())
                    .price(menuItem.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .subtotal(subtotal)
                    .order(order)
                    .build();

            orderItems.add(orderItem);

            totalAmount = totalAmount.add(subtotal);
        }

        order.setOrderItems(orderItems);

        order.setTotalAmount(totalAmount);

        Order saved = orderRepository.save(order);

        return map(saved);
    }
    
    private OrderResponse map(Order order) {

        List<OrderItemResponse> items =
                order.getOrderItems()
                        .stream()
                        .map(item -> OrderItemResponse.builder()
                                .id(item.getId())
                                .menuItemId(item.getMenuItemId())
                                .menuItemName(item.getMenuItemName())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .subtotal(item.getSubtotal())
                                .build())
                        .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerPhone(order.getCustomerPhone())
                .customerAddress(order.getCustomerAddress())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(items)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
    
    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {

        return map(orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found with id : " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByStatus(OrderStatus status) {

        return orderRepository.findByStatus(status)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public OrderResponse updateStatus(Long id, OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found with id : " + id));

        order.setStatus(status);

        return map(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found with id : " + id));

        orderRepository.delete(order);
    }

}