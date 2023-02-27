package com.retailer.shop.test;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleSearchEngineApplicationTests {

	@Test
	void contextLoads() {
		try {
			Document doc = Jsoup.connect("http:localhost:8080/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
