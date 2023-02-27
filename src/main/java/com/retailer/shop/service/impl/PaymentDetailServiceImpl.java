package com.retailer.shop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.retailer.shop.entity.PaymentDetail;
import com.retailer.shop.repo.PaymentDetailRepository;
import com.retailer.shop.service.PaymentDetailService;

/**
 * 
 * Class to write the payment details core logic
 *
 */
@Service
public class PaymentDetailServiceImpl implements PaymentDetailService{

    private final PaymentDetailRepository paymentDetailRepository;

    public PaymentDetailServiceImpl(PaymentDetailRepository paymentDetailRepository) {
    	this.paymentDetailRepository=paymentDetailRepository;
    }
	
	@Override
	public Page<PaymentDetail> findAllPaymentsPageable(Pageable pageable) {
        return paymentDetailRepository.findAll(pageable);
	}

}
