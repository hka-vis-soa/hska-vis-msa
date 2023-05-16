package com.hska.eshop.categoryservice.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("productClient")
    public WebClient product() {
        return WebClient.create("${PRODUCT_SERVICE_URL:localhost:8001}");
    }

}
