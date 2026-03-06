package com.example.reactiveStudentMngSystem.application.port.in;

import com.example.reactiveStudentMngSystem.domain.dto.DivisionDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.DivisionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DivisionUseCase {
    Mono<DivisionResponseDto> addDivision(DivisionDto dto);
    Flux<DivisionResponseDto> getAll();
    Mono<DivisionResponseDto> getById(Long id);
    Mono<DivisionResponseDto> update(Long id, DivisionDto dto);
    Mono<String> delete(Long id);
}