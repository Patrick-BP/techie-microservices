package com.techie.microservices.inventory.controller;

import com.techie.microservices.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class inventoryController {

    private final InventoryService inventoryService;
}
