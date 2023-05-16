package com.hska.eshop.categoryservice.webclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RequestClient {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private final Logger logger = LoggerFactory.getLogger(RequestClient.class);

    @Qualifier("productClient")
    private final WebClient productClient;

    public RequestClient(WebClient productClient) {
        this.productClient = productClient;
    }

    /**
     * Checks if there are products linked to the given categoryId
     * @param categoryId
     * @return true if there are no linked products, false if not
     */
    public boolean verifyProductsForCategory(Long categoryId) {
        String path = "/v1/products/category/" + categoryId + "/verify";
        logger.info("RequestClient.verifyProductsForCategory called with: " + categoryId);
        return Boolean.TRUE.equals(productClient.get().uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(boolean.class)
                .block(REQUEST_TIMEOUT));
    }
}
