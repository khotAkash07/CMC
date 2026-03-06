package com.example.reactiveStudentMngSystem.application.port.out;

import com.example.reactiveStudentMngSystem.domain.model.Division;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DivisionRepositoryPort {
    Mono<Division> save(Division division);
    Flux<Division> findAll();
    Mono<Division> findById(Long id);
    Mono<Division> findByCode(String code); // Needed by StudentService!
    Mono<Void> deleteById(Long id);
}
