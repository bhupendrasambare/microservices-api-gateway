package com.api.gateway.filter;

import com.api.gateway.entity.Paths;
import com.api.gateway.entity.PathsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    @Autowired
    PathsRepository repository;

    public List<String> paths = new ArrayList<>();
    private static boolean scanRepo= false;

    public List<String> openApiEndpoint(){
        if(!scanRepo){
            for(Paths p:repository.findAll()){
                paths.add(p.getPath());
            }
            scanRepo = true;
        }
        return paths;
    }
    public Predicate<ServerHttpRequest> isSecure = request ->openApiEndpoint().stream()
            .noneMatch(uri-> request.getURI().getPath().contains(uri));
}
