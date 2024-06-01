package com.ecommerce.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.service.InventoryService;

@RestController
public class InventoryController {
	
	// Wired to inventory service
	@Autowired
	private InventoryService inventoryService;
	
	// Declare here, getMapping, putMapping, etc
	@GetMapping
	private List<Inventory> getAllInventories(){
		return inventoryService.getAllInventory();
	}
	
	// check available product and its quantity
	@GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable int productId) {
        return inventoryService.getInventoryByProductId(productId);
    }
	
	// add new product into inventory (atribut entried : name, price, quantity)
	@PostMapping
	public Inventory addInventory(@RequestBody Inventory inventory) {
		return inventoryService.addInventory(inventory);
	}
	
	// update product quantity (especially restock or stock opname) 
    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable int productId, @RequestParam int quantity) {
        return inventoryService.updateInventory(productId, quantity);
    }
	
    // update product quantity (caused by order or purchase) 
    @PutMapping("/order")
    public String checkoutInventory(@RequestParam int product_id, @RequestParam int quantity) {
        return inventoryService.checkoutInventory(product_id, quantity);
    }
    
}
