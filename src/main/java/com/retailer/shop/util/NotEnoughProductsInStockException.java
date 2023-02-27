package com.retailer.shop.util;

import com.retailer.shop.entity.Product;
/**
 * 
 * Creates a custom exception when product goes out of stock
 *
 */
public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }
    /**
     * prints custom exception message
     * @param product
     */
    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getName(), product.getQuantity()));
    }

}
