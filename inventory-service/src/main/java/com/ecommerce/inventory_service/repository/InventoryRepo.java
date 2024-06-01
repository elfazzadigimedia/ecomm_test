package com.ecommerce.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.inventory_service.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
	
	@Query("select i from inventory i where i.product_id = ?1") 
	Inventory findByProductId(Integer productId);
	
}
