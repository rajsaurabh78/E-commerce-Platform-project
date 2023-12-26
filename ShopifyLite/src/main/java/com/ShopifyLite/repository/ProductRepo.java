package com.ShopifyLite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Product;

public interface ProductRepo extends MongoRepository<Product,Integer>{
	List<Product> findByName(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByType(String type);
	List<Product> findByTypeContaining(String type);
	List<Product> findByPriceBetween(int min, int max);


}
