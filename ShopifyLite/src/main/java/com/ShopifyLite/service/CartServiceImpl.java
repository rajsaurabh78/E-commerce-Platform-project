package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.CartException;
import com.ShopifyLite.exception.LoginException;
import com.ShopifyLite.exception.ProductException;
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
	public String addItem(Integer pId,String size) throws LoginException{
		//checking user exist or not
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));

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
			if(s<1) {
				throw new ProductException("Inviled quantity avalible quantity : "+s);
			}
			//we get cart of user
			Cart cart=user.getCart();
			//find list of product of that user
			List<ProductDetails> productList=cart.getProductDetailsList();
			if(productList.size()==0) {
				ProductDetails productDet=new ProductDetails();
				productDet.setSize(size);
				productDet.setQuantity(1);
				productDet.setPid(pId);
				productDet.setPrice(productDet.getQuantity()*product.getPrice());
				productDet.setCart(cart);
				List<ProductDetails> plist=new ArrayList<>();
				plist.add(productDet);
				cart.setAmount(cart.getAmount()+productDet.getPrice());
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
					productDet.setQuantity(1);
					productDet.setPid(pId);
					productDet.setPrice(productDet.getQuantity()*product.getPrice());
					productDet.setCart(cart);	
					cart.setAmount(cart.getAmount()+productDet.getPrice());
					cartRepo.save(cart);
					productDetailsRepo.save(productDet);
				}	
			}
			return "product added.";
		}else
			throw new ProductException("Invilid product id : "+pId);

	}

	@Override
	@Transactional
	@Modifying
	public String deleteItem(Integer pId,String size) throws LoginException{

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		Optional<Product> opt2=productRepo.findById(pId);
		if(opt2.isPresent()) {
			List<ProductDetails> pd=user.getCart().getProductDetailsList();
			// Use Stream API to find the product to remove
		    Optional<ProductDetails> productToRemove = pd.stream()
		            .filter(p -> p.getPid() == pId && p.getSize().equalsIgnoreCase(size))
		            .findFirst();		    
		    if (productToRemove.isPresent()) {
		        // Remove the product from the list and delete it from the database
		        ProductDetails productDetails = productToRemove.get();
		        pd.remove(productDetails);
		        productDetailsRepo.delete(productDetails);
		        return "Product removed.";
		    }else
		    	throw new CartException("Item not present in cart.");
		}else
			throw new ProductException("Invilid product id : "+pId);
		
	}

	@Override
	public List<ProductDetails> getAllCartItem()throws LoginException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		List<ProductDetails> list=user.getCart().getProductDetailsList().stream().filter(p->p.getCart()!=null).toList();
		if(list.size()>0) {
			return list;
		}else
			throw new ProductException("Empty list.");
		
	}

	@Override
	public ProductDetails UpdatingProductQuantities(Integer pId,Integer quantity) throws LoginException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		Optional<ProductDetails> opt= user.getCart().getProductDetailsList()
				.stream().filter(s->s.getPid()==pId).findFirst();
		if(opt.isEmpty()) {
			throw new ProductException("Inviled product id.");
		}
		ProductDetails proDetails=opt.get();
		proDetails.setQuantity(quantity);
		return productDetailsRepo.save(proDetails);
	}

}
