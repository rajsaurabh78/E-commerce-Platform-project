package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Cart;

public interface QuantityRepo extends MongoRepository<Cart,Integer>{

}
