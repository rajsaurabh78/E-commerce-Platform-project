package com.ShopifyLite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ShopifyLite.DTO.UpdateProductDTO;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<String> addProductController(@Valid @RequestPart Product product,
	        @RequestPart("image") MultipartFile image){
		String res=productService.addProduct(product, image);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	@DeleteMapping("/product/{pId}")
	public ResponseEntity<String> deleteProductController(@Valid @PathVariable("pId")Integer pId){
		String res=productService.deleteProduct(pId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PatchMapping("/update/product")
	public ResponseEntity<String> updateProductController(@Valid @RequestBody UpdateProductDTO updateProductDTO){
		String res=productService.updateProduct(updateProductDTO);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/product/{pId}")
	public ResponseEntity<Product> getProductByIdController(@Valid @PathVariable("pId")Integer pId){
		Product res=productService.getProductById(pId);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	

}
