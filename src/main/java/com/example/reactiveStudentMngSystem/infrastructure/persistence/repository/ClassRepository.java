package com.example.reactiveStudentMngSystem.infrastructure.persistence.repository;

import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.ClassesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClassRepository extends ReactiveCrudRepository<ClassesEntity, Long> {
    Mono<ClassesEntity> findByCode(String code);
}