package com.ShopifyLite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.exception.LoginException;
import com.ShopifyLite.model.ProductDetails;
import com.ShopifyLite.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
	@PostMapping("/order")
	public ResponseEntity<String> addItemController(@Valid @RequestParam String[] sizeList,@Valid @RequestParam Integer[] quantityList,
			@Valid @RequestParam Integer[] pidList)throws LoginException{
		String res=orderService.addOrder(pidList, sizeList, quantityList);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@GetMapping("/order/allproduct")
	public ResponseEntity<List<ProductDetails>> getAllProductController(){
		List<ProductDetails> res=orderService.allorder();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/order/cancel")
	public ResponseEntity<String> deleteOrderController(@Valid @RequestParam("pId") Integer[] pId){
		String res=orderService.deleteOrder(pId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}
