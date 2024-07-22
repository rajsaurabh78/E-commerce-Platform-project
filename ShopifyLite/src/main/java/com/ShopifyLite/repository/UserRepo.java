package com.ShopifyLite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShopifyLite.model.Product;
import com.ShopifyLite.model.Users;

public interface UserRepo extends JpaRepository<Users,Integer>{
	
	List<Users> findByName(String name);
	Optional<Users> findByEmail(String email);
	Optional<Users> findByPhone(String phone);
	List<Users> findByNameContaining(String name);
	
}
