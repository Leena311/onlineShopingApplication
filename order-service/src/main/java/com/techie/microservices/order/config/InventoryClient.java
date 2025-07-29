package com.techie.microservices.order.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@FeignClient(name="inventory-service",fallbackFactory = InventoryClientFallbackFactory.class)

public interface InventoryClient {

    //check for loadbalancing
    @GetMapping("/api/inventory/getInstance")
    public String getInventoryInstanceInfo() ;
    @GetMapping("/api/inventory/getStock")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) ;



}
