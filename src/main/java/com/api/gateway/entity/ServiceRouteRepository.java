package com.api.gateway.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRouteRepository extends JpaRepository<Services, Long> {
    List<Services> findByEnabledTrue();
}
