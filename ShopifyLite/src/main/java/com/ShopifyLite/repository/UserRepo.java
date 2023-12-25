package com.ShopifyLite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ShopifyLite.model.Users;

public interface UserRepo extends MongoRepository<Users,Integer>{
	
	List<Users> findByName(String name);
	Optional<Users> findByEmail(String email);
	Optional<Users> findByPhone(String phone);
	
}
