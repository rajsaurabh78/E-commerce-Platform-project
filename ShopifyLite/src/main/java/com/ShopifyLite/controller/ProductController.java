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

import com.ShopifyLite.model.Product;
import com.ShopifyLite.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<String> addProductController(@Valid @RequestBody Product product){
		String res=productService.addProduct(product);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	@DeleteMapping("/product/{pId}")
	public ResponseEntity<String> deleteProductController(@Valid @PathVariable("pId")Integer pId){
		String res=productService.deleteProduct(pId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<String> updateProductController(@Valid @RequestBody Product product){
		String res=productService.updateProduct(product);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/product/{name}")
	public ResponseEntity<List<Product>> getProductByNameController(@Valid @PathVariable ("name") String name){
		List<Product> res=productService.getProductByName(name);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/products/{type}")
	public ResponseEntity<List<Product>> getProductByTypeController(@Valid @PathVariable ("type") String type){
		List<Product> res=productService.getProductByType(type);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/productsp/{price}")
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
