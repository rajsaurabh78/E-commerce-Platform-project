package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.ProductException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Cart;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.repository.CartRepo;
import com.ShopifyLite.repository.ProductRepo;
import com.ShopifyLite.repository.UserRepo;

import jakarta.transaction.Transactional;
@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CartRepo cartRepo;

	@Override
	@Transactional
	public String addItem(Integer userId, Integer pId) {

		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			Users user=opt.get();
			Optional<Product> opt2=productRepo.findById(pId);
			if(opt2.isPresent()) {
				Product product=opt2.get();
				List<Product> list=new ArrayList<>();
				list.add(product);
				Cart cart=user.getCart();
				cart.setProductList(list);
				product.setCart(cart);
				productRepo.save(product);
				cartRepo.save(cart);
				user.setCart(cart);
				userRepo.save(user);
				return "product added.";
			}else
				throw new ProductException("Invilid product id : "+pId);
		}else
			throw new UserException("Invilid user id : "+userId);
	}

	@Override
	public String deleteItem(Integer userId, Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllCartItem(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
