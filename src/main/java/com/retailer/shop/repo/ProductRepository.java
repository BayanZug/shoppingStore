package com.retailer.shop.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.shop.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long >{

	Page<Product> findByNameContains(String name, Pageable pageble);
	List<Product> findTop5ByOrderByDiscountDesc();
}
