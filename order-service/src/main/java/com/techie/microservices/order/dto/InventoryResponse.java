package com.techie.microservices.order.dto;

public record InventoryResponse(Long id,String skuCode, boolean isInStock) {
}