package com.ShopifyLite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ShopifyLite.model.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{
	List<Product> findByName(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByType(String type);
	List<Product> findByTypeContaining(String type);
	List<Product> findByPriceBetween(int min, int max);
	//@Query(value="select s.type,q.total from quantity q inner join size s on(q.sId=s.sId) where q.pid=?",nativeQuery =true )
	 @Modifying
	 @Query(value="UPDATE Quantity q SET q.total = :total WHERE q.pid =:pid AND q.sid = (SELECT sid FROM Size WHERE type =:sizeType)",nativeQuery =true)
	 int updateTotal(@Param("total") int total, @Param("pid") int pid, @Param("sizeType") String sizeType);
}
