package com.ShopifyLite.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.DTO.UpdateUserDTO;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.service.ProductService;
import com.ShopifyLite.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@PatchMapping("/update")
	public ResponseEntity<?> updateUserController(@Valid @RequestBody UpdateUserDTO updateUserDTO) throws LoginException{
		String res=userService.updateUser(updateUserDTO);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/add/{amount}")
	public ResponseEntity<?> addAmountController(@Valid @PathVariable ("amount")Integer amount) throws LoginException{
		String res=userService.addAmount(amount);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	@PutMapping("/withdraw/{amount}")
	public ResponseEntity<?> withdrawAmountController(@Valid @PathVariable ("amount")Integer amount) throws LoginException{
		String res=userService.withdrawAmount(amount);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> getUserController() throws LoginException{
		Users res=userService.getUser();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/products/{name}")
	public ResponseEntity<List<Product>> getProductByNameController(@Valid @PathVariable ("name") String name){
		List<Product> res=productService.getProductByName(name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/products/type/{type}")
	public ResponseEntity<List<Product>> getProductByTypeController(@Valid @PathVariable ("type") String type){
		List<Product> res=productService.getProductByType(type);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/products/price/{price}")
	public ResponseEntity<List<Product>> getProductByPriceController(@Valid @PathVariable ("price") Integer price){
		List<Product> res=productService.getProductByPrice(price);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/allproduct/{page}/{NoOfItem}")
	public ResponseEntity<List<Product>> getAllProductController(@Valid @PathVariable ("page")Integer page,
			@Valid @PathVariable ("NoOfItem")Integer NoOfItem,
			@Valid @RequestParam String type,@Valid @RequestParam String direction){
		List<Product> res=productService.getAllProduct(direction, type, page, NoOfItem);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

}
