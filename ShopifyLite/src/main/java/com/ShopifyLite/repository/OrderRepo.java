package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Cart;

public interface OrderRepo extends MongoRepository<Cart,Integer>{

}
