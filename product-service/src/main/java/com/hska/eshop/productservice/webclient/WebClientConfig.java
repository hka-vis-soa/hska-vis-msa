package com.hska.eshop.productservice.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean("categoryClient")
	public WebClient category() {
		String uri = System.getenv("CATEGORY_SERVICE_URL");
		return WebClient.create(uri == null || uri.isEmpty() ? "http://localhost:8000" : uri);
	}
}
