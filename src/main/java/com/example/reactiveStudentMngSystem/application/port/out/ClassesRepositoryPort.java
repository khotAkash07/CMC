package com.example.reactiveStudentMngSystem.application.port.out;

import com.example.reactiveStudentMngSystem.domain.model.Classes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassesRepositoryPort {
    Mono<Classes> save(Classes classes);
    Flux<Classes> findAll();
    Mono<Classes> findById(Long id);
    Mono<Classes> findByCode(String code);
    Mono<Void> deleteById(Long id);
}