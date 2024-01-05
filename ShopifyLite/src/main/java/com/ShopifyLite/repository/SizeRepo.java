package com.ShopifyLite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShopifyLite.model.Cart;

public interface SizeRepo extends JpaRepository<Cart,Integer>{

}
