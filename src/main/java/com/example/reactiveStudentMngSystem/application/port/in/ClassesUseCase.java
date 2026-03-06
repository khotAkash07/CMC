package com.example.reactiveStudentMngSystem.application.port.in;

import com.example.reactiveStudentMngSystem.domain.dto.ClassDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.ClassResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassesUseCase {
    Mono<ClassResponseDto> addClass(ClassDto dto);
    Flux<ClassResponseDto> getAll();
    Mono<ClassResponseDto> getById(Long id);
    Mono<ClassResponseDto> update(Long id, ClassDto dto);
    Mono<String> delete(Long id);
}