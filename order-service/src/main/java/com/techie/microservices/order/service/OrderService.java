package com.techie.microservices.order.service;
import com.techie.microservices.order.config.InventoryClient;
import com.techie.microservices.order.dto.ApiResponse;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.dto.OrderResponse;
import com.techie.microservices.order.entity.Order;
import com.techie.microservices.order.mapper.OrderMapper;
import com.techie.microservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryClient client;
   // #check for loadbalancing
    public  String findInventoryInstanceInfo(){

              return client.getInventoryInstanceInfo();
   }
    public ApiResponse<OrderResponse> createOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.toEntity(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());
        Order saved = orderRepository.save(order);
        OrderResponse response = OrderMapper.toResponse(saved);
        return new ApiResponse<>("Order placed successfully", response);
    }


public void placeOrder(OrderRequest orderRequest) {
    ResponseEntity<Boolean> inStock = client.isInStock(orderRequest.skuCode(), orderRequest.quantity());
    boolean isInStock = inStock.getBody() != null && inStock.getBody();

    if (isInStock) {
        Order order = OrderMapper.toEntity(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());
        Order saved = orderRepository.save(order);
        OrderResponse response = OrderMapper.toResponse(saved);

    } else {
        throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock.");
    }
}



}
