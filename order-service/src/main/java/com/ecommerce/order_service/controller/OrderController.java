package com.ecommerce.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	private String addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
}
