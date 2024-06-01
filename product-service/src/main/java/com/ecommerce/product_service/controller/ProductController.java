package com.ecommerce.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	private List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	private ResponseEntity<Product> getProductDetails(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@PostMapping
    public Product addProduct(@RequestBody Product product) {
		System.out.println(product.getId());
		System.out.println(product.getName());
        return productService.addProduct(product);
    }
	
}
