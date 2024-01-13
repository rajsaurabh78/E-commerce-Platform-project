package com.ShopifyLite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PutMapping("/register")
	public ResponseEntity<?> updateUserController(@Valid @RequestBody Users user){
		String res=userService.updateUser(user);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/register/{id}")
	public ResponseEntity<?> deleteUserController(@Valid @PathVariable ("id")Integer id){
		String res=userService.deleteUser(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/register/{userId}/{amount}")
	public ResponseEntity<?> addAmountController(@Valid @PathVariable ("userId")Integer userId,
			@Valid @PathVariable ("amount")Float amount){
		String res=userService.addAmount(userId, amount);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	@PutMapping("/registers/{userId}/{amount}")
	public ResponseEntity<?> withdrawAmountController(@Valid @PathVariable ("userId")Integer userId,
			@Valid @PathVariable ("amount")Integer amount){
		String res=userService.withdrawAmount(userId, amount);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/register/{userId}")
	public ResponseEntity<?> getUserByIdController(@Valid @PathVariable ("userId")Integer userId){
		Users res=userService.getUserById(userId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/registers/{name}")
	public ResponseEntity<List<Users>> getUserByNameController(@Valid @PathVariable ("name")String name){
		List<Users> res=userService.getUserByName(name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/registerss/{phone}")
	public ResponseEntity<?> getUserByMobileController(@Valid @PathVariable ("phone")String phone){
		Users res=userService.getUserByMobile(phone);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/registersss/{email}")
	public ResponseEntity<?> getUserByEmailController(@Valid @PathVariable ("email")String email){
		Users res=userService.getUserByEmail(email);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/register/{page}/{NoOfItem}")
	public ResponseEntity<List<Users>> getAllUserController(@Valid @PathVariable ("page")Integer page,
			@Valid @PathVariable ("NoOfItem")Integer NoOfItem,
			@Valid @RequestParam String field,@Valid @RequestParam String direction){
		List<Users> res=userService.getAllUser(direction, field, page, NoOfItem);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
