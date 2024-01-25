package com.example.backend.Order;

import org.springframework.stereotype.Component;

@Component
public class OrderFactory {

    public OrderDTO makeOrderDTO(OrderEntity order){
        return OrderDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .user(order.getUser())
                .product(order.getProduct())
                .build();
    }
}