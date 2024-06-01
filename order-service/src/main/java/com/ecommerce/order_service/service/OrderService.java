package com.ecommerce.order_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepo;
import com.ecommerce.order_service.response.InventoryResponse;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String addOrder(Order order) {
		InventoryResponse inventoryResponse = restTemplate.getForObject("http://localhost:8084/{id}", InventoryResponse.class, order.getProduct_id());
		if (inventoryResponse != null) {
			if (inventoryResponse.getQuantity() == 0) return "quantity not enough";
		} else return "product not available";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("product_id", String.valueOf(order.getProduct_id()));
		params.put("quantity", String.valueOf(order.getQuantity()));
		// check quantity and update if available 
		ResponseEntity<String> respStr = restTemplate.exchange("http://localhost:8084/order?product_id={product_id}&quantity={quantity}", HttpMethod.PUT, null, String.class, params);
		if (respStr.getBody().equals("OK")) {
			orderRepo.save(order);
			return "Order success";
		} else return respStr.getBody();
		
	}
	
}
