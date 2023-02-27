package com.retailer.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.shop.entity.PaymentDetail;
@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
	
}
