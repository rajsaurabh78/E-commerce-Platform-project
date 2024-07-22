package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShopifyLite.DTO.UpdateUserDTO;
import com.ShopifyLite.exception.AmountException;
import com.ShopifyLite.exception.LoginException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Address;
import com.ShopifyLite.model.Authority;
import com.ShopifyLite.model.Cart;
import com.ShopifyLite.model.Order;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.repository.CartRepo;
import com.ShopifyLite.repository.OrderRepo;
import com.ShopifyLite.repository.UserRepo;

import jakarta.transaction.Transactional;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	@Transactional
	public Users addUser(Users user) {
		//
		user.setPassword(encoder.encode(user.getPassword()));
		List<Authority> auths=user.getAuthorities();
		if(auths.size()==0) {
			List<Authority> aut=new ArrayList<>();
			Authority at=new Authority();
			at.setName("ROLE_USER");
			at.setUser(user);
			aut.add(at);
			user.setAuthorities(aut);
		}else {
			for(Authority i:auths) {
			i.setName("ROLE_"+i.getName().toUpperCase());
			i.setUser(user);
			}
		}
		
		Cart cart=new Cart();
		Order order=new Order();
		cart.setAmount(0);
		cartRepo.save(cart);
		order.setTotalPrice(0);
		orderRepo.save(order);
		user.setOrder(order);
		user.setCart(cart);
		user.setAmount(0.0);
		List<Address> addressList=user.getAddressList();
		for(Address a:addressList) {
			a.setUser(user);
		}
		Users newUser=userRepo.save(user);
//		return "User saves with userId : "+newUser.getUserId();
		return newUser;
	}

	@Override
	public String updateUser(UpdateUserDTO updateUserDTO) throws LoginException {

			//Here we get my user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		//updated its field
		if(updateUserDTO.getDob()!=null) {
			updateUserDTO.setDob(user.getDob());
		}
		if(updateUserDTO.getEmail()!=null) {
			updateUserDTO.setEmail(user.getEmail());
		}
		if(updateUserDTO.getName()!=null) {
			updateUserDTO.setName(user.getName());
		}
		//Here update only if password is there
		if(updateUserDTO.getPassword()!=null) {
			updateUserDTO.setPassword(encoder.encode(user.getPassword()));
		}
		if(updateUserDTO.getPhone()!=null) {
			updateUserDTO.setPhone(user.getPhone());
		}
		//Saved updated user
		userRepo.save(user);
		return "user updated successfully";
	}

	@Override
	public String deleteUser(Integer userId) {
		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			//Here we get my user and deleted
			userRepo.delete(opt.get());
			return "deleted successfully";
		}else
			throw new UserException("Inviled userId.");
	}

	@Override
	public String addAmount(Integer amount) throws LoginException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
			user.setAmount(user.getAmount()+amount);
			userRepo.save(user);
			return "Amount added successfully";
	}

	@Override
	public String withdrawAmount(Integer amount) throws LoginException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		//Here we check withdraw amount is > amount or not
		if(user.getAmount()>amount) {
			//and withdraw amount
			user.setAmount(user.getAmount()-amount);
			userRepo.save(user);
			return "Amount withdrawal successfully";
		}else
			throw new AmountException("Insufficient balance.");
	}

	@Override
	public Users getUser() throws LoginException {
		//Here we use optional class and if user found return it else throw exception.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepo.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
	}

	@Override
	public List<Users> getUserByName(String name) {
		// Here we use findBy... Syntex
		
		List<Users> list= userRepo.findByName(name);
		if(list.isEmpty()) {
			throw new UserException("No user found.");
		}else
			return list;
	}

	@Override
	public Users getUserByMobile(String phone) {
		
		Optional<Users> opt= userRepo.findByPhone(phone);
		if(opt.isPresent()) {
			return opt.get();
		}else
			throw new UserException("No user found.");
		
	}
	@Override
	public Users getUserByEmail(String email) {
		
		Optional<Users> opt= userRepo.findByEmail(email);
		if(opt.isEmpty()) {
			throw new UserException("No user found.");
		}else
			return opt.get();
	}

	@Override
	public List<Users> getAllUser(String direction, String field, Integer page, Integer NoOfItem) {
		
		if(direction.toUpperCase().equals("ASC")||direction.toUpperCase().equals("DSC")) {
			PageRequest p = PageRequest.of(page-1, NoOfItem, direction.toUpperCase().equals("ASC") ? Sort.by(field).ascending() : Sort.by(field).descending());
			Page<Users> pageObj= userRepo.findAll(p);
			List<Users> users= pageObj.getContent();
			if(users.isEmpty()) {
				throw new UserException("No user found .");
			}else 
				return users;
			
		}else
			throw new UserException("Inviled direction .");	
		
	}

}
