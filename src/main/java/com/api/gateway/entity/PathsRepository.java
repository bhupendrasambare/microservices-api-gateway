package com.api.gateway.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathsRepository extends JpaRepository<Paths,Long> {
}
