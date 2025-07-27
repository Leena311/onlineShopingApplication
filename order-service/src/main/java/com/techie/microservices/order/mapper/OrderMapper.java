package com.techie.microservices.order.mapper;

import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.dto.OrderResponse;
import com.techie.microservices.order.entity.Order;

import java.util.UUID;

public class OrderMapper {
    String generatedOrderNumber = UUID.randomUUID().toString();

    public  static OrderResponse toResponse(Order order){
        if(order==null){
            return  null;

        }

        return   new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        );

    }
    public static Order toEntity(OrderRequest orderRequest) {
        Order order=Order.builder()
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
        return order;

    }
}
