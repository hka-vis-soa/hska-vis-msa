package com.hska.eshop.productservice;

import com.hska.eshop.openapi.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({OpenAPIConfiguration.class})
public class ProductServiceApplication {
	public static void main(String[] args) { SpringApplication.run(ProductServiceApplication.class, args);}
}
