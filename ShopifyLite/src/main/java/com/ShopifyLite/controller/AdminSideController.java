package com.ShopifyLite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.DTO.Admin;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/admin")
public class AdminSideController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Users> addAdminController(@Valid @RequestBody Admin admin){
		
		Users res=userService.addAdmin(admin);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{name}")
	public ResponseEntity<List<Users>> getUserByNameController(@Valid @PathVariable ("name")String name){
		List<Users> res=userService.getUserByName(name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/phone/{phone}")
	public ResponseEntity<?> getUserByMobileController(@Valid @PathVariable ("phone")String phone){
		Users res=userService.getUserByMobile(phone);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/user/email/{email}")
	public ResponseEntity<?> getUserByEmailController(@Valid @PathVariable ("email")String email){
		Users res=userService.getUserByEmail(email);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/user/{page}/{NoOfItem}")
	public ResponseEntity<List<Users>> getAllUserController(@Valid @PathVariable ("page")Integer page,
			@Valid @PathVariable ("NoOfItem")Integer NoOfItem,
			@Valid @RequestParam String field,@Valid @RequestParam String direction){
		List<Users> res=userService.getAllUser(direction, field, page, NoOfItem);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserController(@Valid @PathVariable ("id")Integer id){
		String res=userService.deleteUser(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}
