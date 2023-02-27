package com.retailer.shop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.retailer.shop.entity.PaymentDetail;
import com.retailer.shop.entity.Product;
import com.retailer.shop.entity.User;
import com.retailer.shop.repo.PaymentDetailRepository;
import com.retailer.shop.repo.ProductRepository;
import com.retailer.shop.repo.UserRepo;
import com.retailer.shop.service.ShoppingCartService;
import com.retailer.shop.util.NotEnoughProductsInStockException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 *
 * 
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final UserRepo userRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository,PaymentDetailRepository paymentDetailRepository,UserRepo userRepository) {
        this.productRepository = productRepository;
        this.paymentDetailRepository=paymentDetailRepository;
        this.userRepository=userRepository;
    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {

        if (products.containsKey(product)) {
            if (products.get(product) > 1) {
                products.replace(product, products.get(product) - 1);
            }
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * pay will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void pay() throws NotEnoughProductsInStockException {
        Product product;
        
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findById(entry.getKey().getId()).orElse(null);
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
        PaymentDetail paymentDetail = new PaymentDetail();
        String name = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            name = authentication.getName();
        }
        if(name!=null) {

        paymentDetail.setAmount(getTotal());
        paymentDetail.setUserName(name);
        }
        if(name!=null)
        paymentDetailRepository.save(paymentDetail);
        productRepository.saveAll(products.keySet());
        productRepository.flush();
        products.clear();
    }

    @Override
    public Double getTotal() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getDiscountPrice()*(Double.valueOf(entry.getValue())))
                .sum();
    			
    }
    @Override
    public void removeAllCart() {
        productRepository.flush();
        products.clear();
    			
    }
}
