package com.hska.eshop.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

	@Bean
	public OpenAPI eshopAPI() {
		return new OpenAPI()
				.info(new Info().title("eshop Application")
						.description("eshop API Documentation")
						.version("v1.0.0")
						.license(new License().name("Apache 2.0").url("https://springdoc.org")));
	}
}