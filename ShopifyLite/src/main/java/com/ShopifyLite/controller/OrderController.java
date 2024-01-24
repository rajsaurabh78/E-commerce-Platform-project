package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
	@PostMapping("/order/{userId}/{quantity}")
	public ResponseEntity<String> addItemController(@Valid @PathVariable("userId") Integer userId,
			@Valid @PathVariable("quantity") Integer quantity,@Valid @RequestParam String size,
			@Valid @RequestParam Integer[] pidList){
		String res=orderService.addOrder(userId, pidList, size, quantity);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	
}
