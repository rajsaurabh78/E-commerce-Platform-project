package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.Product;

public interface CartService{
	
	public String addItem(Integer userId,Integer cId,Integer pId);
	public String deleteItem(Integer pId);
	public List<Product> getAllCartItem(Integer cId);
	
}
