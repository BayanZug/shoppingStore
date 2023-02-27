package com.retailer.shop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.retailer.shop.entity.Product;
import com.retailer.shop.service.ProductService;
import com.retailer.shop.util.Pager;

/**
 *Default page controller that loads all the product data and hosts product images if exists
 */
@Controller
public class HomeController {
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class) ;
	@Autowired
	ProductService productService;
    private static final int INITIAL_PAGE = 0;
    
    @Value("${spring.products.perpage}")
    public int PRODUCTS_PER_PAGE;
    
    @Value("${spring.products.currency}")
    public String PRODUCT_CURRENCY;

	
	@RequestMapping(value = "/")
	public String viewHomePage(Model model,@RequestParam("page") Optional<Integer> page,HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "searchString",required = false)String searchString) {
		
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        HttpSession sessionObj = request.getSession(false);
        //check session exist or not
        //if not available create new session
        if (sessionObj == null) {
            logger.info("Session not available, creating new session.");
            sessionObj = request.getSession(true);
        }
        String activeSessions = sessionObj.getAttribute("activeSessions")!=null
                ?sessionObj.getAttribute("activeSessions").toString()
                :"0";
        
        logger.info("Current active sessions are "+activeSessions);
        sessionObj.setAttribute("currency", PRODUCT_CURRENCY);

		List<Product> featuredProducts = productService.findFeaturedProduct();
		model.addAttribute("featuredProducts",featuredProducts);
		 Page<Product> products = null;
		if(searchString==null)
	        products = productService.findAllProductsPageable(PageRequest.of(evalPage, PRODUCTS_PER_PAGE));
		else
			products = productService.findSearchProductsPageable(PageRequest.of(evalPage, PRODUCTS_PER_PAGE),searchString);
        Pager pager = new Pager(products);

        model.addAttribute("products", products);
        model.addAttribute("pager", pager);

		return "index";
	}
	 @RequestMapping(value = "/productImage/{image_id}")
	    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException, SQLException {
	        final HttpHeaders  headers = new HttpHeaders();
	    	Product prod = productService.get(imageId);
	        byte[] imageContent = null;
	    	if(prod!=null&&prod.getPhoto()!=null) {
	    	imageContent = prod.getPhoto();
	    	}
	        headers.setContentType(MediaType.IMAGE_PNG);
	        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	    }
}
