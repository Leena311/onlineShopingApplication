package com.techie.microservices.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/inventory")
    public ResponseEntity<String> inventoryFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Inventory Service is currently unavailable. Please try again later.");
    }
    @GetMapping("/fallback/order")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Order Service is currently unavailable. Please try again later.");
    }
    @GetMapping("/fallback/product")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Product Service is currently unavailable. Please try again later.");
    }
}