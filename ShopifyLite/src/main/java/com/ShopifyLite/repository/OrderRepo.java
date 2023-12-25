package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Cart;
import com.ShopifyLite.model.Order;

public interface OrderRepo extends MongoRepository<Order,Integer>{

}
