package org.example.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 1. Cấu hình tuyến đường thủ công cho product-service
                .route("product-route", r -> r
                        .path("/api/products/**") // Bất kỳ request nào bắt đầu bằng /api/products/
                        .uri("lb://product-service") // Tự động lấy danh sách IP từ Eureka và Load Balance
                )

                .route("order-route", r -> r
                        .path("/api/orders/**")
                        .uri("lb://order-service")
                )
                .route("user-route", r -> r
                        .path("/api/users/**")
                        .uri("lb://user-service")
                )


                .build();
    }
}
