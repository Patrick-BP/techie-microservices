package com.techie.microservices.inventory.services;

import com.techie.microservices.inventory.dto.InventoryRequest;
import com.techie.microservices.inventory.dto.InventoryResponse;
import com.techie.microservices.inventory.models.Inventory;
import com.techie.microservices.inventory.repositories.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Boolean isInStock(String skuCode, int quantity){
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

    public InventoryResponse addInventory(InventoryRequest inventoryRequest){
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());
        inventoryRepository.save(inventory);
        return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity());
    }


}
