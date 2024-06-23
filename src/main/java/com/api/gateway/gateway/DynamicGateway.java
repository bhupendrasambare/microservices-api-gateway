package com.api.gateway.gateway;

import com.api.gateway.entity.ServiceRouteRepository;
import com.api.gateway.entity.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DynamicGateway {
    @Autowired
    private ServiceRouteRepository serviceRouteRepository;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routesBuilder = builder.routes();

        List<Services> serviceRoutes = serviceRouteRepository.findByEnabledTrue();
        for (Services serviceRoute : serviceRoutes) {
            routesBuilder.route(serviceRoute.getServiceId(),
                    r -> r.path(serviceRoute.getPath())
                            .filters(f -> f
                                    .circuitBreaker(c -> c
                                            .setName("cb-" + serviceRoute.getServiceId())
                                            .setFallbackUri("forward:/fallback?service=" + serviceRoute.getServiceId())))
                            .uri(serviceRoute.getUri()));
        }

        return routesBuilder.build();
    }
}
