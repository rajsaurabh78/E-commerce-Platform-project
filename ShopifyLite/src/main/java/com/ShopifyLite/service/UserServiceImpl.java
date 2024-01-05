package com.ShopifyLite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.AmountException;
import com.ShopifyLite.exception.UserException;
import com.ShopifyLite.model.Authority;
import com.ShopifyLite.model.Users;
import com.ShopifyLite.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public String addUser(Users user) {
		//
		List<Authority> auths=user.getAuthorities();
		for(Authority i:auths) {
			i.setName("ROLE_"+i.getName().toUpperCase());
			i.setUser(user);
		}
		Users u=userRepo.save(user);
		return "User saves with userId : "+u.getUserId();
	}

	@Override
	public String updateUser(Users user) {
		//
		Optional<Users> opt=userRepo.findById(user.getUserId());
		if(opt.isPresent()) {
			//Here we get my user
			Users usr=opt.get();
			//updated its field
			usr.setDob(user.getDob());
			usr.setEmail(user.getEmail());
			usr.setName(user.getName());
			//
			usr.setPassword(user.getPassword());
			usr.setPhone(user.getPhone());
			//Saved updated user
			userRepo.save(usr);
			return "user updated successfully";
		}else
			throw new UserException("Inviled userId.");
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
	public String addAmount(Integer userId, Integer amount) {
		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			//Here we get my user and add amount
			Users user=opt.get();
			user.setAmount(user.getAmount()+amount);
			userRepo.save(user);
			return "Amount added successfully";
		}else
			throw new UserException("Inviled userId.");
	}

	@Override
	public String withdrawAmount(Integer userId, Integer amount) {
		Optional<Users> opt=userRepo.findById(userId);
		if(opt.isPresent()) {
			//Here we get my user
			Users user=opt.get();
			//Here we check withdraw amount is > amount or not
			if(user.getAmount()>amount) {
				//and withdraw amount
				user.setAmount(user.getAmount()-amount);
				userRepo.save(user);
				return "Amount added successfully";
			}else
				throw new AmountException("Insufficient userId.");
		}else
			throw new UserException("Inviled userId.");
	}

	@Override
	public Users getUserById(Integer userId) {
		//Here we use optional class and if user found return it else throw exception.
		return userRepo.findById(userId).orElseThrow(()->new UserException("Inviled userId"));
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
