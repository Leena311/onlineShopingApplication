package com.techie.microservices.inventory.mapper;

import com.techie.microservices.inventory.dto.InventoryRequest;
import com.techie.microservices.inventory.dto.InventoryResponse;
import com.techie.microservices.inventory.entity.Inventory;

public class InventoryMapper {
    public static Inventory toEntity(InventoryRequest inventoryRequest){
        if(inventoryRequest==null){
            return null;
        }
       Inventory inventory =Inventory.builder()
               .skuCode(inventoryRequest.getSkuCode())
               .quantity(inventoryRequest.getQuantity())
               .build();
        return  inventory;
    }
    public static InventoryResponse toResponse(Inventory inventory){
        if(inventory==null){
            return null;
        }
        return  new InventoryResponse(
                inventory.getId(),
                inventory.getSkuCode(),
                inventory.getQuantity() >= 1

        );
    }

}
