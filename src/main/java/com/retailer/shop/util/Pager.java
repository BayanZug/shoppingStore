package com.retailer.shop.util;


import org.springframework.data.domain.Page;

import com.retailer.shop.entity.Product;

/**
 * 
 * Pagination wrapper for product
 *
 */
public class Pager {

    private final Page<Product> products;

    public Pager(Page<Product> products) {
        this.products = products;
    }

    public int getPageIndex() {
        return products.getNumber() + 1;
    }

    public int getPageSize() {
        return products.getSize();
    }

    public boolean hasNext() {
        return products.hasNext();
    }

    public boolean hasPrevious() {
        return products.hasPrevious();
    }

    public int getTotalPages() {
        return products.getTotalPages();
    }

    public long getTotalElements() {
        return products.getTotalElements();
    }

    public boolean indexOutOfBounds() {
        return this.getPageIndex() < 0 || this.getPageIndex() > this.getTotalElements();
    }

}