package com.ShopifyLite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ShopifyLite.model.Cart;

import jakarta.transaction.Transactional;

public interface CartRepo extends JpaRepository<Cart,Integer>{
	@Modifying
	@Transactional
	@Query(value=" delete from product_cart_list where product_list_pid = :pid and cart_list_cid = :cid",nativeQuery =true)
	int deleteItem(@Param("pid") int pid, @Param("cid") int cid);

}
