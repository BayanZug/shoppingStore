package com.retailer.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.retailer.shop.entity.PaymentDetail;

/**
 * 
 * Payment detail service
 *
 */
public interface PaymentDetailService {

	Page<PaymentDetail> findAllPaymentsPageable(Pageable pageable);

}
