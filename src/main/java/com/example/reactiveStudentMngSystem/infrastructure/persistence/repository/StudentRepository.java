package com.example.reactiveStudentMngSystem.infrastructure.persistence.repository;

import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Long> {

    Mono<StudentEntity> findByName(String name);
    Flux<StudentEntity> findByActiveTrue();


}
