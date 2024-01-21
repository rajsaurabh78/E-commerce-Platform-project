package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.Product;

public interface CartService{
	
	public String addItem(Integer userId,Integer pId,String size,Integer quantity);
	public String deleteItem(Integer userId,Integer pId);
	public List<Product> getAllCartItem(Integer userId);
	
}
