package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Product;

public interface ProductRepo extends MongoRepository<Product,Integer>{

}
