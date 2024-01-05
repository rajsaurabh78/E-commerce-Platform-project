package com.ShopifyLite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShopifyLite.model.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{

}
