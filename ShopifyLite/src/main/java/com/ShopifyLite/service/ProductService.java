package com.ShopifyLite.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ShopifyLite.DTO.UpdateProductDTO;
import com.ShopifyLite.model.Product;

public interface ProductService{
	
	public String addProduct(Product product,MultipartFile image);
	public String updateProduct(UpdateProductDTO updateProductDTO);
	public String deleteProduct(Integer pId);
	public Product getProductById(Integer pId);
	public List<Product> getProductByName(String name);
	public List<Product> getProductByType(String type);
	public List<Product> getProductByPrice(Integer price);
	public List<Product> getAllProduct(String direction,String type,Integer page,Integer No);
	
}
