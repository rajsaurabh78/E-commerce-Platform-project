package com.ShopifyLite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.LoginException;
import com.ShopifyLite.model.ProductDetails;

@Service
public interface OrderService{
	public String addOrder(Integer[] pidList,String[] size,Integer[] quantity)throws LoginException;
	public String deleteOrder(Integer[] pid)throws LoginException;
	public List<ProductDetails> allorder();
}
