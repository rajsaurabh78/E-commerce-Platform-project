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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopifyLite.model.Size;
import com.ShopifyLite.service.SizeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class SizeController {
	
	@Autowired
	private SizeService sizeService;

	
	@PostMapping("/size")
	public ResponseEntity<String> addSizeController(@Valid @RequestBody Size size){
		String res=sizeService.addSize(size);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/size/{sid}")
	public ResponseEntity<String> deleteSizeController(@Valid @PathVariable("sid")Integer sid){
		String res=sizeService.deleteSize(sid);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/size")
	public ResponseEntity<?> getAllSizeController(){
		List<Size> res=sizeService.allSize();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/size")
	public ResponseEntity<String> updateSizeController(@Valid @RequestBody Size size){
		String res=sizeService.updateSize(size);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
