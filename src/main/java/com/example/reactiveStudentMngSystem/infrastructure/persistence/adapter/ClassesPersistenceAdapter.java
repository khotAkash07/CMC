package com.example.reactiveStudentMngSystem.infrastructure.persistence.adapter;

import com.example.reactiveStudentMngSystem.application.port.out.ClassesRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.model.Classes;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.ClassesEntity;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassesPersistenceAdapter implements ClassesRepositoryPort {

    private final ClassRepository classRepository;

    @Override
    public Mono<Classes> save(Classes classes) {
        return classRepository.save(toEntity(classes)) // Model → Entity
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Flux<Classes> findAll() {
        return classRepository.findAll()
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Classes> findById(Long id) {
        return classRepository.findById(id)
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Classes> findByCode(String code) {
        return classRepository.findByCode(code)
                .map(this::toModel);                   // Entity → Model
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return classRepository.deleteById(id);         // No mapping needed
    }

    //Mapper Methods

    private ClassesEntity toEntity(Classes classes) {
        ClassesEntity entity = new ClassesEntity();
        entity.setClass_id(classes.class_id());
        entity.setCode(classes.code());
        entity.setDescription(classes.description());
        return entity;
    }

    private Classes toModel(ClassesEntity entity) {
        return new Classes(entity.getClass_id(), entity.getCode(), entity.getDescription());
    }
}