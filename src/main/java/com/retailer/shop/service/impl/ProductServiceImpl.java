package com.retailer.shop.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.retailer.shop.entity.PaymentDetail;
import com.retailer.shop.entity.Product;
import com.retailer.shop.repo.PaymentDetailRepository;
import com.retailer.shop.repo.ProductRepository;
import com.retailer.shop.service.ProductService;


/**
 * 
 * Product core logic class
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{


	    
	 
	  private final ProductRepository productRepository;
	    private final PaymentDetailRepository paymentDetailRepository;

	    @Autowired
	    public ProductServiceImpl(ProductRepository productRepository,PaymentDetailRepository paymentDetailRepository) {
	        this.productRepository = productRepository;
	        this.paymentDetailRepository = paymentDetailRepository;
	    }

	    @Override
	    public Page<Product> findAllProductsPageable(Pageable pageable) {
	        return productRepository.findAll(pageable);
	    }

	    @Override
	    public Page<PaymentDetail> findAllProductsPayments(Pageable pageable) {
	        return paymentDetailRepository.findAll(pageable);
	    }

	    @Override
	    public Optional<Product> findById(Long id) {
	        return productRepository.findById(id);
	    }
	    
	    public List<Product> listAll() {
	        return productRepository.findAll();
	    }
	     
	    public List<Product> listBySearch(String search) {
	        return productRepository.findAll();
	    }
	     
	    public void save(Product product) {
	        productRepository.save(product);
	    }
	     
	    public Product get(long id) {
	        return productRepository.findById(id).orElse(null);
	    }
	     
	    public void delete(long id) {
	        productRepository.deleteById(id);
	    }
		/**
		 * 
		 *getting top 5 product by discount
		 *
		 */
		@Override
		public List<Product> findFeaturedProduct() {
	        return productRepository.findTop5ByOrderByDiscountDesc();
		}
		/**
		 * 
		 *searching a name by JPA like query
		 *
		 */
		@Override
		public Page<Product> findSearchProductsPageable(Pageable pageble,String searchString) {
			return productRepository.findByNameContains(searchString,pageble);
		}
}
