package com.techie.microservices.order.controller;
import com.techie.microservices.order.dto.ApiResponse;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.dto.OrderResponse;
import com.techie.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //#check for loadbalancing
    @GetMapping("/check-inventory-instance")
    public String checkInventoryServiceInstance() {
        return orderService.findInventoryInstanceInfo();
    }

    @PostMapping("/createOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<OrderResponse>> createOrders(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createOrder(orderRequest),HttpStatus.CREATED) ;

    }
    @PostMapping("/placedOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrders(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);

    }

}
