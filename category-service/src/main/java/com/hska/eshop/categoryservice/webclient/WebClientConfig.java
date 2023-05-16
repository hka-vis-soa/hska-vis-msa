package com.hska.eshop.categoryservice.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("productClient")
    public WebClient product() {
        String uri = System.getenv("PRODUCT_SERVICE_URL");
        return WebClient.create(uri == null || uri.isEmpty() ? "http://localhost:8001" : uri);
    }

}
