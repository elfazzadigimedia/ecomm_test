package com.ecommerce.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepository;
	
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
    	
    	Product prod = new Product();
    	prod.setName(product.getName());
        return productRepository.save(prod);
    }
	
	
}
