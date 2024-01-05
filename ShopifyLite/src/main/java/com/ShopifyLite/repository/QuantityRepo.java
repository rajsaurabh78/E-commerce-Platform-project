package com.ShopifyLite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShopifyLite.model.Quantity;

public interface QuantityRepo extends JpaRepository<Quantity,Integer>{

}
