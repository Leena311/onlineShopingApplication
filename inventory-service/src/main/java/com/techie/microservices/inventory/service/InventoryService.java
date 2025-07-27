package com.techie.microservices.inventory.service;

import com.techie.microservices.inventory.dto.ApiResponse;
import com.techie.microservices.inventory.dto.InventoryRequest;
import com.techie.microservices.inventory.dto.InventoryResponse;
import com.techie.microservices.inventory.entity.Inventory;
import com.techie.microservices.inventory.mapper.InventoryMapper;
import com.techie.microservices.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    // Find an inventory for a given skuCode where quantity >= 0

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
    public ApiResponse<InventoryResponse> saveInStock(InventoryRequest inventoryRequest) {
        Inventory inventoryEntry = InventoryMapper.toEntity(inventoryRequest);
        Inventory saved = inventoryRepository.save(inventoryEntry);
        InventoryResponse response = InventoryMapper.toResponse(saved);
        return new ApiResponse<>("Inventory  created  successfully", response);

    }
}

