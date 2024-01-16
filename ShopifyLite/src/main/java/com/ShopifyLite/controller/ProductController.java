package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.Size;
import com.ShopifyLite.service.ProductService;
import com.ShopifyLite.service.SizeService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private SizeService sizeService;
	
	@PostMapping("/product")
	public ResponseEntity<String> addProductController(@Valid @RequestBody Product product){
		String res=productService.addProduct(product);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/size")
	public ResponseEntity<String> addSizeController(@Valid @RequestBody Size size){
		String res=sizeService.addSize(size);
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

}
