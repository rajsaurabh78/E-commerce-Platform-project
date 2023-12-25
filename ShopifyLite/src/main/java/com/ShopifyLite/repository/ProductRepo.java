package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Cart;

public interface ProductRepo extends MongoRepository<Cart,Integer>{

}
