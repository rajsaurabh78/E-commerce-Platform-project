package com.ShopifyLite.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.ShopifyLite.DTO.UpdateUserDTO;
import com.ShopifyLite.model.Users;

public interface UserService{

	public Users addUser(Users user);
	public String updateUser(UpdateUserDTO updateUserDTO) throws LoginException;
	public String deleteUser(Integer userId);
	public String addAmount(Integer amount)throws LoginException ;
	public String withdrawAmount(Integer amount) throws LoginException;
	public Users getUser() throws LoginException;
	public List<Users> getUserByName(String name);
	public Users getUserByMobile(String phone);
	public Users getUserByEmail(String email);
	public List<Users> getAllUser(String direction ,String field,Integer page ,Integer NoOfItem);
	
}
