package com.ecommerce.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.product_service.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
}
