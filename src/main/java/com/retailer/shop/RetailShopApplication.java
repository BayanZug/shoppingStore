package com.retailer.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;


/** 
 *Main Class which runs the app
 */
@ServletComponentScan
@SpringBootApplication
public class RetailShopApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	public static void main(String[] args) {

		SpringApplication.run(RetailShopApplication.class, args);
		
	}

}
