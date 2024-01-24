package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.AmountException;
import com.ShopifyLite.exception.ProductException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Order;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.ProductDetails;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.repository.OrderRepo;
import com.ShopifyLite.repository.ProductDetailsRepo;
import com.ShopifyLite.repository.ProductRepo;
import com.ShopifyLite.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo;

	@Override
	@Transactional
	public String addOrder(Integer userId, Integer[] pidList,String size,Integer quantity) {
	//	List<Product> plist=new ArrayList<>();
		for(Integer pid:pidList) {
			Optional<Users> opt=userRepo.findById(userId);
			if(opt.isPresent()) {
				//we got user
				Users user=opt.get();
				//checking product exist or not
				Optional<Product> opt2=productRepo.findById(pid);
				if(opt2.isPresent()) {
					//we got product
					Product product=opt2.get();
					int s;
					try {
						int x=productRepo.getSizes(size, pid);
						s=x;
					} catch (Exception e) {
						throw new ProductException("Inviled size.");
					}
					if(s<quantity) {
						throw new ProductException("Inviled quantity avalible quantity : "+s);
					}
					int totalPrice=quantity*product.getPrice();
					Float a=totalPrice-user.getAmount();
					if(totalPrice>user.getAmount()) {
						throw new AmountException("Insufficient amount (Add anount : "+a+")");
					}
					user.setAmount(user.getAmount()-totalPrice);
					//we get order of user
					Order order=user.getOrder();
					//find list of product of that user
					ProductDetails productDet=new ProductDetails();
					productDet.setSize(size);
					productDet.setQuantity(quantity);
					productDet.setPid(pid);
					productDet.setPrice(totalPrice);
					productRepo.updateTotal(s-quantity,pid, size);
					productDet.setOrder(order);
					List<ProductDetails> plist=new ArrayList<>();
					plist.add(productDet);
					order.setTotalPrice(order.getTotalPrice()+productDet.getPrice());
					order.setProductDetailsList(plist);	
					productDetailsRepo.save(productDet);
					userRepo.save(user);
						
				}else
					throw new ProductException("Invilid product id : "+pid);
			}else
				throw new UserException("Invilid user id : "+userId);
		}
		return "ordered.";
	}

	@Override
	public String deleteOrder(Integer userId, Integer[] pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> allorder() {
		// TODO Auto-generated method stub
		return null;
	}

}
