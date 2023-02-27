package com.retailer.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.retailer.shop.entity.PaymentDetail;
import com.retailer.shop.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Product detail service
 *
 */

public interface ProductService {

	Optional<Product> findById(Long id);

	Page<Product> findAllProductsPageable(Pageable pageable);
	
	List<Product> findFeaturedProduct();

	List<Product> listAll();

	List<Product> listBySearch(String search);

	void save(Product product);

	Product get(long id);

	void delete(long id);

	Page<Product> findSearchProductsPageable(Pageable of, String searchString);

	Page<PaymentDetail> findAllProductsPayments(Pageable pageable);
}
