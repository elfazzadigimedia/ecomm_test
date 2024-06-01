package com.ecommerce.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order_service.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
