package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.ProductException;
import com.ShopifyLite.exception.SizeException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.Quantity;
import com.ShopifyLite.model.Size;
import com.ShopifyLite.repository.ProductRepo;
import com.ShopifyLite.repository.QuantityRepo;
import com.ShopifyLite.repository.SizeRepo;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;
	
    @Autowired
    private QuantityRepo quantityRepo;
    
    @Autowired
    private SizeRepo sizeRepo;

	@Override
	@Transactional
	public String addProduct(Product product) {
		String str=product.getSizeQuan();
        List<Quantity> qList=new ArrayList<>();
        String[] pairs = str.split(",");
        for (int i=0;i<pairs.length-1;i+=2) {
    		Optional<Size> opt=sizeRepo.findByType(pairs[i].toLowerCase());
    		if(opt.isPresent()) {
    			Quantity q=new Quantity();
    			Size size=opt.get();
    			q.setTotal(Integer.parseInt(pairs[i + 1]));
    			q.setSize(size);
    			q.setProduct(product);
    			qList.add(q);
    			
    		}else
    			throw new SizeException("Inviled size.");
        }
        
        product.setQuantityList(qList);
        product.setSizeQuan(null);
        quantityRepo.saveAll(qList);
		productRepo.save(product);
		return "Product added.";

	}

	@Override
	@Transactional
	public String updateProduct(Product product) {

		Optional<Product> opt=productRepo.findById(product.getPid());
		if(opt.isEmpty()) {
			throw new ProductException("Inviled product Id");
		}else {
			Product p=opt.get();
			if(product.getManufactureDate()!=null) {
				p.setManufactureDate(product.getManufactureDate());
			}
			if(product.getName()!=null) {
				p.setName(product.getName());
			}
			if(product.getPrice()!=null) {
				p.setPrice(product.getPrice());
			}
			if(product.getType()!=null) {
				p.setType(product.getType());
			}
			
			if(product.getSizeQuan()!=null) {
				List<Quantity> qList=new ArrayList<>();
				String str=product.getSizeQuan();
		        String[] pairs = str.split(",");
		        for (int i=0;i<pairs.length-1;i+=2) {
		        	int x=productRepo.updateTotal(Integer.parseInt(pairs[i + 1]), product.getPid(),pairs[i].toLowerCase());
		    		if(x==0) {
			        	Optional<Size> opt2=sizeRepo.findByType(pairs[i].toLowerCase());
			    		if(opt2.isPresent()) {
			    			Quantity q=new Quantity();
			    			Size size=opt2.get();
			    			q.setTotal(Integer.parseInt(pairs[i + 1]));
			    			q.setSize(size);
			    			q.setProduct(product);
			    			qList.add(q);
			    			
			    		}else
			    			throw new SizeException("Inviled size.");
		    		}
		        }
		        product.setQuantityList(qList);
		        quantityRepo.saveAll(qList);
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
