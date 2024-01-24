package com.ShopifyLite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShopifyLite.model.Size;

public interface SizeRepo extends JpaRepository<Size,Integer>{
	Optional<Size> findByType(String type);
}
