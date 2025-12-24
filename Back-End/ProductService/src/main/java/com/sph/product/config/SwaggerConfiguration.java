package com.sph.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	    @Bean
	    OpenAPI openAPI(){
	        return new OpenAPI().info(new Info().title("Hotel Meta Data").version("1.0"));
	    }
}
