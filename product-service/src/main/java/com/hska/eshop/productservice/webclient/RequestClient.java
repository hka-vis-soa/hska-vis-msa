package com.hska.eshop.productservice.webclient;

import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
@Component
public class RequestClient {

	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
	private final Logger logger = LoggerFactory.getLogger(RequestClient.class);

	@Qualifier("categoryClient")
	private final WebClient categoryClient;

	public RequestClient(WebClient categoryClient) { this.categoryClient = categoryClient; }

	public boolean doesCategoryExist(Long categoryId) {
		logger.info("RequestClient#doesCategoryExist#sendRequestForCategoryWithId#" + categoryId);
		String path = "/v1/categories/" + categoryId + "/verify";
		this.logger.info("RequestClient#doesCategoryExist#path: " + path);
		return Boolean.TRUE.equals(categoryClient.get().uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(boolean.class)
				.block(REQUEST_TIMEOUT));
	}
}
