package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.AmountException;
import com.ShopifyLite.exception.LoginException;
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
	public String addOrder(Integer[] pidList,String[] sizeList,Integer[] quantityList) throws LoginException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
	    List<ProductDetails> plist = new ArrayList<>();
	    Order order = user.getOrder();
        for (int i = 0; i < pidList.length; i++) {
            Integer pid = pidList[i];
            String size = sizeList[i];
            Integer quantity = quantityList[i];

            Product product = productRepo.findById(pid)
                    .orElseThrow(() -> new ProductException("Invalid product id: " + pid));
            int availableQuantity=0;
            try {
				int as=productRepo.getSizes(size, pid);
				availableQuantity+=as;
				
			} catch (Exception e) {
				throw new ProductException("Inviled size.");
			}
            int totalPrice = quantity * product.getPrice();
            Double remainingAmount = user.getAmount() - totalPrice;
            if (availableQuantity < quantity) {
	                throw new ProductException("Invalid quantity, available quantity: " + availableQuantity);
	        }
            if (remainingAmount < 0) {
            	throw new AmountException("Insufficient amount, add amount: " + Math.abs(remainingAmount));
            }
	  
            user.setAmount(remainingAmount);
            

            ProductDetails productDet = new ProductDetails();
            productDet.setSize(size);
            productDet.setQuantity(quantity);
            productDet.setPid(pid);
            productDet.setPrice(product.getPrice());
            productDet.setOrder(order);

            productRepo.updateTotal(availableQuantity - quantity, pid, size);
            plist.add(productDet);

            order.setTotalPrice(order.getTotalPrice() + productDet.getPrice());
            order.setProductDetailsList(plist);
        }

        // Save user and order outside the loop
        
        productDetailsRepo.saveAll(plist);
        userRepo.save(user);
        return "ordered.";	
		
	}

	@Override
	@Transactional
	public String deleteOrder(Integer[] pid) throws LoginException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		Integer amount=0;
		for(Integer id:pid) {
			ProductDetails productDetails = productDetailsRepo.findById(id)
					.orElseThrow(() -> new ProductException("Invalid product id: " + pid));
			amount+=productDetails.getPrice()*productDetails.getQuantity();
			
			int size=productRepo.getSizes(productDetails.getSize(),id);
			productRepo.updateTotal(size+productDetails.getQuantity(),id,productDetails.getSize());
			productDetailsRepo.delete(productDetails);
		}
		user.setAmount(user.getAmount()+amount);
		userRepo.save(user);
		
		return "Oreder successfully canceled.";
	}

	@Override
	public List<ProductDetails> allorder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		List<ProductDetails> list=user.getOrder().getProductDetailsList().stream()
				.filter(p->p.getCart()==null).toList();
		if(list.size()>0) {
			return list;
		}else
			throw new ProductException("Empty list.");
	
	}

}

