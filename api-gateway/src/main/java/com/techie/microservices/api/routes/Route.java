package com.techie.microservices.api.routes;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class Route {

    //Aggregate  All REST API in Gateway
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-api",r->r
                        .path("/api/product/**")
                        .uri("lb://product-service"))

                .route("product-swagger", r -> r
                        .path("/aggregate/product-service/**")
                        //.filters(f -> f.rewritePath("/aggregate/product-service/(?<path>.*)", "/${path}"))
                        .filters(f -> f.rewritePath("/aggregate/product-service/v3/api-docs", "/v3/api-docs"))
                        .uri("lb://product-service"))

                .route("order-api", r -> r
                        .path("/api/order/**")
                        .uri("lb://order-service"))

                .route("order-swagger", r -> r
                        .path("/aggregate/order-service/**")
                        //.filters(f -> f.rewritePath("/aggregate/order-service/(?<path>.*)", "/${path}"))
                        .filters(f -> f.rewritePath("/aggregate/order-service/v3/api-docs", "/v3/api-docs"))
                        .uri("lb://order-service"))
                .route("inventory-api",r->r
                        .path("/api/inventory/**")
                        .uri("lb://inventory-service"))

                .route("inventory-swagger", r -> r
                        .path("/aggregate/inventory-service/**")
                        //.filters(f -> f.rewritePath("/aggregate/inventory-service/(?<path>.*)", "/${path}"))
                        .filters(f -> f.rewritePath("/aggregate/inventory-service/v3/api-docs", "/v3/api-docs"))
                        .uri("lb://inventory-service"))

                .build();
    }
}


