package com.retailer.shop.util;


import org.springframework.data.domain.Page;

import com.retailer.shop.entity.PaymentDetail;

/**
 * 
 *  Pagination wrapper for payments
 *
 */
public class PaymentPager {

    private final Page<PaymentDetail> paymentDetail;

    public PaymentPager(Page<PaymentDetail> paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public int getPageIndex() {
        return paymentDetail.getNumber() + 1;
    }

    public int getPageSize() {
        return paymentDetail.getSize();
    }

    public boolean hasNext() {
        return paymentDetail.hasNext();
    }

    public boolean hasPrevious() {
        return paymentDetail.hasPrevious();
    }

    public int getTotalPages() {
        return paymentDetail.getTotalPages();
    }

    public long getTotalElements() {
        return paymentDetail.getTotalElements();
    }

    public boolean indexOutOfBounds() {
        return this.getPageIndex() < 0 || this.getPageIndex() > this.getTotalElements();
    }

}