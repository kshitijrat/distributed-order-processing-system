package com.project.backend.inventory.service;

import com.project.backend.inventory.entity.Inventory;
import com.project.backend.inventory.repo.InventoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Check stock availability
    public boolean isInStock(Long productId, int requiredQty) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));

        return inventory.getQuantity() >= requiredQty;
    }

    // Reduce stock after successful payment
    public Inventory reduceStock(Long productId, int qty) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getQuantity() < qty) {
            throw new RuntimeException("Insufficient stock");
        }

        inventory.setQuantity(inventory.getQuantity() - qty);
        return inventoryRepository.save(inventory);
    }

    // Add new product stock (admin use)
    public Inventory addStock(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
