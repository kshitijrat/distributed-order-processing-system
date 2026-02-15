package com.project.backend.inventory.controller;

import com.project.backend.inventory.entity.Inventory;
import com.project.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // Add product stock
    @PostMapping
    public Inventory addStock(@RequestBody Inventory inventory) {
        return inventoryService.addStock(inventory);
    }

    // Check stock
    @GetMapping("/check/{productId}/{qty}")
    public boolean checkStock(@PathVariable Long productId,
                              @PathVariable int qty) {
        return inventoryService.isInStock(productId, qty);
    }

    // Reduce stock
    @PutMapping("/reduce/{productId}/{qty}")
    public Inventory reduceStock(@PathVariable Long productId,
                                 @PathVariable int qty) {
        return inventoryService.reduceStock(productId, qty);
    }
}
