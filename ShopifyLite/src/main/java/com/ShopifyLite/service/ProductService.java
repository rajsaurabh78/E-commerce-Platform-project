package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.Product;

public interface ProductService{
	
	public String addProduct(Product product);
	public String updateProduct(Product product);
	public String deleteProduct(Integer pId);
	public List<Product> getProductByName(String name);
	public List<Product> getProductByType(String type);
	public List<Product> getProductByPrice(String name,Integer price);
	public List<Product> getAllProduct(String direction,String type,Integer page,String No);
	
}
