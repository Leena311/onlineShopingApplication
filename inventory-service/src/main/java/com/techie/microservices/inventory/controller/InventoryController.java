package com.techie.microservices.inventory.controller;

import com.techie.microservices.inventory.dto.ApiResponse;
import com.techie.microservices.inventory.dto.InventoryRequest;
import com.techie.microservices.inventory.dto.InventoryResponse;
import com.techie.microservices.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    //check for loadbalancing
    @Value("${server.port}")
    private String port;
    @Autowired
    private InventoryService inventoryService;
//check for loadbalancing
    @GetMapping("/getInstance")
    public String getInventoryInstanceInfo() {
        return "application is up on port : " + port;
    }

    @PostMapping("/create")
    @ResponseStatus
    public ResponseEntity<ApiResponse<InventoryResponse>> createinventory(@RequestBody InventoryRequest inventoryRequest) {
        ApiResponse<InventoryResponse> response = inventoryService.saveInStock(inventoryRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getStock")
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        boolean available = inventoryService.isInStock(skuCode, quantity);
        return ResponseEntity.ok(available);
    }
}
