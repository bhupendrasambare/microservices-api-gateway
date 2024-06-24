package com.api.gateway.filter;

import com.api.gateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Value("${server.port}")
    private String port;

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecure.test(exchange.getRequest())) {
                // validate header
                if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (token != null && token.startsWith("Bearer ")) {
                        token = token.substring(7);
                        // validate token in auth service
                        try {
                            jwtUtils.validateToken(token);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return handleUnauthorized(exchange);
                        }
                    } else {
                        return handleUnauthorized(exchange);
                    }
                } else {
                    return handleUnauthorized(exchange);
                }
            }
            return chain.filter(exchange);
        }));
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {

    }
}
