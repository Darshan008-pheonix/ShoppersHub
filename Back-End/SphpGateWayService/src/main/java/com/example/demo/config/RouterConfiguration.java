package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
@Configuration
public class RouterConfiguration {
    @Bean
    public RouterFunction<ServerResponse> ownerServiceRoute() {
        return GatewayRouterFunctions.route("OWNERSERVICE")
                .route(RequestPredicates.path("/owners/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("OWNERSERVICE"))
                .filter(FilterFunctions.stripPrefix(1))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("PRODUCTSERVICE")
                .route(RequestPredicates.path("/product/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("PRODUCTSERVICE"))
                .filter(FilterFunctions.stripPrefix(1))
                .build();
    }
}
