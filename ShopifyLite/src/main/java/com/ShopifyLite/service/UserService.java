package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.Users;

public interface UserService{

	public String addUser(Users user);
	public String updateUser(Users user);
	public String deleteUser(Integer userId);
	public String addAmount(Integer userId,Integer amount);
	public String withdrawAmount(Integer userId,Integer amount);
	public Users getUserById(Integer userId);
	public List<Users> getUserByName(String name);
	public Users getUserByMobile(String phone);
	public Users getUserByEmail(String email);
	public List<Users> getAllUser(String direction ,String field,Integer page ,Integer NoOfItem);
	
}
