package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.Users;
import com.ShopifyLite.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Users> addUserController(@Valid @RequestBody Users user){
		Users res=userService.addUser(user);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
}
