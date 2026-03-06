package com.example.reactiveStudentMngSystem.infrastructure.persistence.repository;

import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.DivisionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DivisionRepo extends ReactiveCrudRepository<DivisionEntity, Long> {

    Mono<DivisionEntity> findByCode(String code);
}