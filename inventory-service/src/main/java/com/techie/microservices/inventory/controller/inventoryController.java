package com.techie.microservices.inventory.controller;

import com.techie.microservices.inventory.dto.InventoryRequest;
import com.techie.microservices.inventory.dto.InventoryResponse;
import com.techie.microservices.inventory.services.InventoryService;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class inventoryController {

    private final InventoryService inventoryService;

    public inventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity){
        return inventoryService.isInStock(skuCode, quantity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse addInventory(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.addInventory(inventoryRequest);
    }
}
