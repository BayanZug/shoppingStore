package com.retailer.shop.service;


import java.util.Map;

import com.retailer.shop.entity.Product;
import com.retailer.shop.util.NotEnoughProductsInStockException;

/**
 * 
 * Shopping cart service for adding and removing
 *
 */

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void pay() throws NotEnoughProductsInStockException;

    Double getTotal();

	void removeAllCart();
}
