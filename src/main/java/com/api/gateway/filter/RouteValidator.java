package com.api.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoint = List.of(
            "/auth/register",
            "/auth/validate",
            "/auth/login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecure = request ->openApiEndpoint.stream()
            .noneMatch(uri-> request.getURI().getPath().contains(uri));
}
