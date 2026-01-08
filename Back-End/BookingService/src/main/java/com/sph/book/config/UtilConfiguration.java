package com.sph.book.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
