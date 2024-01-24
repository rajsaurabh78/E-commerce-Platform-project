package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.ProductDetails;

public interface CartService{
	
	public String addItem(Integer userId,Integer pId,String size);
	public String deleteItem(Integer userId,Integer pId,String size);
	public List<ProductDetails> getAllCartItem(Integer userId);
	
}
