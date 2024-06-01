package com.ecommerce.inventory_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepo;
import com.ecommerce.inventory_service.response.ProductResponse;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;
	
	// This is used for communication between services
	@Autowired
    private RestTemplate restTemplate;
	
	public List<Inventory> getAllInventory(){
		return inventoryRepo.findAll();
	}
	
	public Inventory getInventoryByProductId(int productId) {
		return inventoryRepo.findByProductId(productId);
	}

	public Inventory addInventory(Inventory inventory) {
		ProductResponse product = restTemplate.getForObject("http://localhost:8083/product/{id}", ProductResponse.class, inventory.getProduct_id());
		inventory.setName(product.getName());
		return inventoryRepo.save(inventory);
	}
	
	public Inventory updateInventory(int productId, int quantity) {
        Inventory inventory = inventoryRepo.findByProductId(productId);
        ProductResponse product = restTemplate.getForObject("http://localhost:8083/product/{id}", ProductResponse.class, productId);
        inventory.setQuantity(quantity);
        inventory.setName(product.getName());
        return inventoryRepo.save(inventory);
    }
	
	public String checkoutInventory(int product_id, int quantity) {
		Inventory currentInv = inventoryRepo.findByProductId(product_id);
		if (quantity > currentInv.getQuantity()) {
			return "available product not enough";
		} else {
			int balanceInv = currentInv.getQuantity() - quantity;
			// update balance stock
			updateInventory(product_id, balanceInv);
			return "OK";
		}
	}
	
	
	
}
