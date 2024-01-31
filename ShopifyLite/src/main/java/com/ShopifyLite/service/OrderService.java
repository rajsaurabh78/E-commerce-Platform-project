package com.ShopifyLite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ShopifyLite.model.ProductDetails;

@Service
public interface OrderService{
	public String addOrder(Integer userId, Integer[] pidList,String[] size,Integer[] quantity);
//	public String deleteOrder(Integer userId,Integer[] pid);
	public List<ProductDetails> allorder(Integer userId);
}
