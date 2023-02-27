package com.retailer.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.retailer.shop.entity.Product;
import com.retailer.shop.service.ProductService;
import com.retailer.shop.service.ShoppingCartService;
import com.retailer.shop.util.NotEnoughProductsInStockException;

/**
 * Bean based Shopping cart controller
 */
@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        modelAndView.addObject("totalNum", shoppingCartService.getTotal());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/pay")
    public ModelAndView pay() {
        try {
            shoppingCartService.pay();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        catch (Exception e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
		}
        return shoppingCart();
    }
    @GetMapping("/shoppingCart/removeAll")
    public ModelAndView removeAll() {
        try {
            shoppingCartService.removeAllCart();
        } catch (Exception e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        
        return shoppingCart();
    }
    @GetMapping("/product/{productId}")
	public ModelAndView showEditProductForm(@PathVariable(name = "productId") Long productId) {
		ModelAndView mav = new ModelAndView("view_product");

		Product product = productService.get(productId);
		mav.addObject("product", product);

		return mav;
	}
}
