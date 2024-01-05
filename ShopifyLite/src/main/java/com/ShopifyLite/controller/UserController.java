package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.Users;
import com.ShopifyLite.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> addUserController(@Valid @RequestBody Users user){
		String res=userService.addUser(user);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}

}
