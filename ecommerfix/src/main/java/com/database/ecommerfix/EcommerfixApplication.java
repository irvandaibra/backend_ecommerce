package com.database.ecommerfix;

import com.database.ecommerfix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerfixApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerfixApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

}
