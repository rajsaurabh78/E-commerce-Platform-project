package com.ShopifyLite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.ProductException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.repository.ProductRepo;
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public String addProduct(Product product) {
		
		productRepo.save(product);
		return "Product added.";
	}

	@Override
	public String updateProduct(Product product) {

		Optional<Product> opt=productRepo.findById(product.getPId());
		if(opt.isEmpty()) {
			throw new ProductException("Inviled product Id");
		}else {
			Product p=opt.get();
			if(p.getManufactureDate()!=null) {
				p.setManufactureDate(product.getManufactureDate());
			}
			if(p.getName()!=null) {
				p.setName(product.getName());
			}
			if(p.getPrice()!=null) {
				p.setPrice(product.getPrice());
			}
			if(p.getType()!=null) {
				p.setType(product.getType());
			}
		
			productRepo.save(p);
			return "Updated";
		}
			
		
	}

	@Override
	public String deleteProduct(Integer pId) {
	
		Optional<Product> opt=productRepo.findById(pId);
		if(opt.isEmpty()) {
			throw new ProductException("Inviled product Id");
		}else {
			Product p=opt.get();
			productRepo.delete(p);
			return "Deleted";
		}
		
	}

	@Override
	public List<Product> getProductByName(String name) {
		
		List<Product> list=productRepo.findByNameContaining(name);
		if(list.isEmpty()) {
			throw new ProductException("No product found.");
		}else {
			return list;
		}
		
	}

	@Override
	public List<Product> getProductByType(String type) {
		List<Product> list=productRepo.findByTypeContaining(type);
		if(list.isEmpty()) {
			throw new ProductException("No product found.");
		}else {
			return list;
		}
		
	}

	@Override
	public List<Product> getProductByPrice(Integer price) {

		List<Product> list=productRepo.findByPriceBetween(1, price);
		if(list.isEmpty()) {
			throw new ProductException("No product found.");
		}else {
			return list;
		}
		
	}

	@Override
	public List<Product> getAllProduct(String direction, String type, Integer page, Integer NoOfItem) {

		if(direction.toUpperCase().equals("ASC")||direction.toUpperCase().equals("DSC")) {
			PageRequest p = PageRequest.of(page-1, NoOfItem, direction.toUpperCase().equals("ASC") ? Sort.by(type).ascending() : Sort.by(type).descending());
			Page<Product> pageObj= productRepo.findAll(p);
			List<Product> list= pageObj.getContent();
			if(list.isEmpty()) {
				throw new UserException("No item found .");
			}else 
				return list;
			
		}else
			throw new UserException("Inviled direction .");	
		
	}

}
