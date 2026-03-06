package com.example.reactiveStudentMngSystem.application.service;

import com.example.reactiveStudentMngSystem.domain.dto.ClassDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.ClassResponseDto;
import com.example.reactiveStudentMngSystem.application.port.in.ClassesUseCase;
import com.example.reactiveStudentMngSystem.application.port.out.ClassesRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.model.Classes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClassesService implements ClassesUseCase {

    private final ClassesRepositoryPort classesRepositoryPort;

    private ClassResponseDto mapToDto(Classes classes) {
        return new ClassResponseDto(
                classes.class_id(),
                classes.code(),
                classes.description()
        );
    }

    @Override
    public Mono<ClassResponseDto> addClass(ClassDto dto) {

        Classes classes = new Classes(
                null,
                dto.code(),
                dto.description()
        );
        return classesRepositoryPort.save(classes)
                .map(this::mapToDto);
    }

    @Override
    public Flux<ClassResponseDto> getAll() {

        return classesRepositoryPort.findAll()
                .map(this::mapToDto);                  // Classes Model → Dto
    }

    @Override
    public Mono<ClassResponseDto> getById(Long id) {

        return classesRepositoryPort.findById(id)
                .map(this::mapToDto);                  // Classes Model → Dto
    }

    @Override
    public Mono<ClassResponseDto> update(Long id, ClassDto dto) {

        return classesRepositoryPort.findById(id)
                .flatMap(c -> {
                    return classesRepositoryPort.save(new Classes(c.class_id(), dto.code(), dto.description()));
                })
                .map(this::mapToDto);
    }

    @Override
    public Mono<String> delete(Long id) {

        return classesRepositoryPort.deleteById(id)
                .then(Mono.just("Class with id " + id + " was deleted!"));
    }
}