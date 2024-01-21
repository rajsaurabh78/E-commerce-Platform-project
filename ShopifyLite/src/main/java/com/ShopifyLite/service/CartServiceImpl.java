package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.CartException;
import com.ShopifyLite.exception.ProductException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Cart;
import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.ProductDetails;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.repository.CartRepo;
import com.ShopifyLite.repository.ProductDetailsRepo;
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
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo;

	@Override
	@Transactional
	public String addItem(Integer userId, Integer pId,String size,Integer quantity) {
		//checking user exist or not
		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			//we got user
			Users user=opt.get();
			//checking product exist or not
			Optional<Product> opt2=productRepo.findById(pId);
			if(opt2.isPresent()) {
				//we got product
				Product product=opt2.get();
				int s;
				try {
					int x=productRepo.getSizes(size, pId);
					s=x;
				} catch (Exception e) {
					throw new ProductException("Inviled size.");
				}
				if(s<quantity) {
					throw new ProductException("Inviled quantity avalible quantity : "+s);
				}
				//we get cart of user
				Cart cart=user.getCart();
				//find list of product of that user
				List<ProductDetails> productList=cart.getProductDetailsList();
				if(productList.size()==0) {
					ProductDetails productDet=new ProductDetails();
					productDet.setSize(size);
					productDet.setQuantity(quantity);
					productDet.setPid(pId);
					productDet.setAmount(quantity*product.getPrice());
					productDet.setCart(cart);
					List<ProductDetails> plist=new ArrayList<>();
					plist.add(productDet);
					cart.setAmount(cart.getAmount()+productDet.getAmount());
					cart.setProductDetailsList(plist);	
					productDetailsRepo.save(productDet);
			//		cartRepo.save(cart);
					return "product added.";
				}
				//checking each product for quantity and size
				for(ProductDetails p:productList) {
					//here we check product already present cart or not
					if(p.getPid()==pId&&p.getSize().equals(size.toLowerCase())) {
						//if present we check size is different or not
						throw new ProductException("Product already avalible in cart.");				
					}else {
						ProductDetails productDet=new ProductDetails();
						productDet.setSize(size);
						productDet.setQuantity(quantity);
						productDet.setPid(pId);
						productDet.setAmount(quantity*product.getPrice());
						productDet.setCart(cart);	
						cart.setAmount(cart.getAmount()+productDet.getAmount());
						cartRepo.save(cart);
						productDetailsRepo.save(productDet);
					}	
				}
				return "product added.";
			}else
				throw new ProductException("Invilid product id : "+pId);
		}else
			throw new UserException("Invilid user id : "+userId);
	}

	@Override
	public String deleteItem(Integer userId, Integer pId) {

		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			Users user=opt.get();
			Optional<Product> opt2=productRepo.findById(pId);
			if(opt2.isPresent()) {
				int x=cartRepo.deleteItem(pId,user.getCart().getCid());
				if(x==0) {
					throw new CartException("Item not present in cart.");
				}else
					return "product removed.";
			}else
				throw new ProductException("Invilid product id : "+pId);
		}else
			throw new UserException("Invilid user id : "+userId);
		
	}

	@Override
	public List<Product> getAllCartItem(Integer userId) {

//		Optional<Users> opt=userRepo.findById(userId);
//		if(opt.isPresent()) {
//			Users user=opt.get();
//			List<Product> list= user.getCart().getProductList();
//			if(list.size()>0) {
//				return list;
//			}else
//				throw new ProductException("Empty list.");
//		}else
//			throw new UserException("Invilid user id : "+userId);
		return null;
	}

}
