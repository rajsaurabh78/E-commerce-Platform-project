package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.exception.LoginException;
import com.ShopifyLite.model.ProductDetails;

public interface CartService{
	
	public String addItem(Integer pId,String size)throws LoginException;
	public String deleteItem(Integer pId,String size)throws LoginException;
	public List<ProductDetails> getAllCartItem()throws LoginException;
	public ProductDetails UpdatingProductQuantities(Integer pId,Integer quantity)throws LoginException;
	
}
