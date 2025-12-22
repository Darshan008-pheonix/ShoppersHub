package com.sph.registery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShoppersHubServiceRegisteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppersHubServiceRegisteryApplication.class, args);
	}

}
