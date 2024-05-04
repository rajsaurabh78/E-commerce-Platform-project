package com.ShopifyLite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.ProductDetails;
import com.ShopifyLite.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
	@PostMapping("/order/{userId}")
	public ResponseEntity<String> addItemController(@Valid @PathVariable("userId") Integer userId,
			@Valid @RequestParam String[] sizeList,@Valid @RequestParam Integer[] quantityList,
			@Valid @RequestParam Integer[] pidList){
		String res=orderService.addOrder(userId, pidList, sizeList, quantityList);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@GetMapping("/order/allproduct/{userId}")
	public ResponseEntity<List<ProductDetails>> getAllProductController(@Valid @PathVariable("userId")Integer userId){
		List<ProductDetails> res=orderService.allorder(userId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
}
