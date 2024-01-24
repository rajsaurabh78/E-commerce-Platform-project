package com.ShopifyLite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.ProductDetails;
import com.ShopifyLite.service.CartService;

import jakarta.validation.Valid;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	
	@PostMapping("/cart/{userId}/{pId}")
	public ResponseEntity<String> addItemController(@Valid @PathVariable("userId") Integer userId,
			@Valid @PathVariable("pId") Integer pId,@Valid @RequestParam String size){
		String res=cartService.addItem(userId, pId, size);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart/{userId}/{pId}")
	public ResponseEntity<String> deleteItemController(@Valid @PathVariable("userId") Integer userId,
			@Valid @PathVariable("pId") Integer pId,@Valid @RequestParam String size){
		String res=cartService.deleteItem(userId, pId,size);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<ProductDetails>> getAllCartItemController(@Valid @PathVariable("userId") Integer userId){
		List<ProductDetails> res=cartService.getAllCartItem(userId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
