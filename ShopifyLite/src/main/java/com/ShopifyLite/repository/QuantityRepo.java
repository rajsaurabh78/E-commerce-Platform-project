package com.ShopifyLite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Quantity;

public interface QuantityRepo extends MongoRepository<Quantity,Integer>{

}
