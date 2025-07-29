package com.techie.microservices.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {
    @Override
    public InventoryClient create(Throwable cause) {
        return new InventoryClient() {
            @Override
            public String getInventoryInstanceInfo() {
                log.warn("Fallback: Could not get inventory instance info. Reason: {}", cause.getMessage());
                return "Inventory Service Unavailable";

            }

            @Override
            public ResponseEntity<Boolean> isInStock(String skuCode, Integer quantity) {
                log.warn("Fallback: Inventory check failed for skuCode={}, reason={}", skuCode, cause.getMessage());
                return ResponseEntity.ok(false);
            }
        };
    }
}