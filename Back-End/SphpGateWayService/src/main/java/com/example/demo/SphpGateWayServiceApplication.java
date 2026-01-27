package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SphpGateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SphpGateWayServiceApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p.path("/shp/payment/**")
						.filters(f->f.rewritePath("/shp/payment/(?<segment>.*)", "/${segment}")
						.retry(c->c.setRetries(3))
						.circuitBreaker(c->c.setName("paymentServiceCircuitBreaker")
						.setFallbackUri("forward:/paymentFallBack")))
						.uri("lb://PAYMENTSERVICE"))
				.build();
	}

}
