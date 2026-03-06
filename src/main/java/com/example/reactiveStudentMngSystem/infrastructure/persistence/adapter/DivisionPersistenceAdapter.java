package com.example.reactiveStudentMngSystem.infrastructure.persistence.adapter;

import com.example.reactiveStudentMngSystem.application.port.out.DivisionRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.model.Division;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.DivisionEntity;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.repository.DivisionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DivisionPersistenceAdapter implements DivisionRepositoryPort {

    private final DivisionRepo divisionRepo;

    @Override
    public Mono<Division> save(Division division) {
        return divisionRepo.save(toEntity(division))   // Model → Entity
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Flux<Division> findAll() {
        return divisionRepo.findAll()
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Division> findById(Long id) {
        return divisionRepo.findById(id)
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Division> findByCode(String code) {
        return divisionRepo.findByCode(code)
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return divisionRepo.deleteById(id);
    }

    // Mapper Methods

    private DivisionEntity toEntity(Division division) {
        DivisionEntity entity = new DivisionEntity();
        entity.setDiv_id(division.div_id());
        entity.setCode(division.code());
        entity.setDescription(division.description());
        return entity;
    }

    private Division toModel(DivisionEntity entity) {
        return new Division(entity.getDiv_id(),entity.getCode(),entity.getDescription());
    }
}
