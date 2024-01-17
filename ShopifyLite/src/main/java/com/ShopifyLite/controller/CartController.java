package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.service.CartService;

import jakarta.validation.Valid;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	
	@PostMapping("/cart/{userId}/{pId}")
	public ResponseEntity<String> addItemController(@Valid @PathVariable("userId") Integer userId,
			@Valid @PathVariable("pId") Integer pId){
		String res=cartService.addItem(userId, pId);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
}
