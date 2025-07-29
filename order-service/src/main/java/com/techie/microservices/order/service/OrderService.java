package com.techie.microservices.order.service;
import com.techie.microservices.order.config.InventoryClient;
import com.techie.microservices.order.dto.ApiResponse;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.dto.OrderResponse;
import com.techie.microservices.order.entity.Order;
import com.techie.microservices.order.mapper.OrderMapper;
import com.techie.microservices.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.logging.Logger;


@Service
public class OrderService {
   // private static final Logger log = Logger.getLogger(OrderService.class.getName());

    @Autowired
    private OrderRepository orderRepository;
    //using Feign client
    @Autowired
    private InventoryClient client;
    //using Rest template
//    @Autowired
//    private RestTemplate template;
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackInventoryCheck")
//    public Boolean isInStock(String skuCode, int quantity) {
//        String url = "http://inventory-service/api/inventory/getStock?skuCode=" + skuCode + "&quantity=" + quantity;
//        return template.getForObject(url, Boolean.class);
//    }
//
//    public Boolean fallbackInventoryCheck(String skuCode, int quantity, Throwable t) {
//        log.warn("Fallback triggered: {}", t.getMessage());
//        return false;

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
