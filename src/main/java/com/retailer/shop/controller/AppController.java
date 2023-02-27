package com.retailer.shop.controller;

import com.retailer.shop.entity.PaymentDetail;
import com.retailer.shop.entity.Product;
import com.retailer.shop.service.ProductService;
import com.retailer.shop.util.Pager;
import com.retailer.shop.util.PaymentPager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;


/**
 *Adimn controller for viewing,editing,adding,deleting products and viewing payment list 
 */
@Controller
@RequestMapping("/admin")
public class AppController {
	@Autowired
	private ProductService service;




	public static final Logger logger = LoggerFactory.getLogger(AppController.class);
	@Value("${spring.products.perpage}")
	public int PRODUCTS_PER_PAGE;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAY_PAGE = 0;

	@RequestMapping("/list")
	public String viewHomePage(Model model, @RequestParam("page") Optional<Integer> page) {

		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Product> products = service.findAllProductsPageable(PageRequest.of(evalPage, PRODUCTS_PER_PAGE));
		Pager pager = new Pager(products);

		model.addAttribute("listProducts", products);
		model.addAttribute("pager", pager);

		return "products";
	}

	@RequestMapping("/payments")
	public String payments(Model model, @RequestParam("page") Optional<Integer> page) {
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAY_PAGE : page.get() - 1;

		Page<PaymentDetail> payments = service.findAllProductsPayments(
				PageRequest.of(evalPage, PRODUCTS_PER_PAGE, Sort.by("createdTime").descending()));
		PaymentPager pager = new PaymentPager(payments);
		model.addAttribute("payments", payments);
		model.addAttribute("pager", pager);

		return "payment";
	}

	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			@RequestParam(value = "productPhoto",required = false) MultipartFile multipartFile) {
		Product prod = null;
		Instant now = Instant.now();
		if (product.getId() != null)
			prod = service.get(product.getId());
		
		if (prod != null) {
			product.setId(prod.getId());
			
			if(!(multipartFile!=null && multipartFile.getSize()>0 )&& prod.getPhoto()!=null) {
				product.setPhoto(prod.getPhoto());
			}
		}
		
		String fileName = "";
		if(multipartFile!=null &&multipartFile.getSize()>0 && multipartFile instanceof MultipartFile)
			fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(product.getDiscount()==null)
			bindingResult.rejectValue("discount", "error.discount",
                    "There is value cannot be empty");
		if(product.getPrice()==null)
			bindingResult.rejectValue("price", "error.price",
					"There is value cannot be empty");
		if(product.getQuantity()<=0)
			bindingResult.rejectValue("quantity", "error.quantity",
					"Quantity should be greater than or equal to one");
		if(multipartFile!=null&&multipartFile.getSize()>0) {
			logger.info(fileName);
			try {
				product.setPhoto(multipartFile.getBytes());
				product.setImage(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		
		}
		else {
			logger.error("file is null");
		}
		if (bindingResult.hasErrors()) {
			return "new_product";
		}
		
		try {
			
			service.save(product);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		return "redirect:/admin/list";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");

		Product product = service.get(id);
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);

		return "redirect:/admin/list";
	}
}
