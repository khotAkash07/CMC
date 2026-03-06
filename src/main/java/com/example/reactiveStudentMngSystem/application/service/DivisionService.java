package com.example.reactiveStudentMngSystem.application.service;

import com.example.reactiveStudentMngSystem.domain.dto.DivisionDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.DivisionResponseDto;
import com.example.reactiveStudentMngSystem.application.port.in.DivisionUseCase;
import com.example.reactiveStudentMngSystem.application.port.out.DivisionRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.model.Division;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DivisionService implements DivisionUseCase {

    private final DivisionRepositoryPort divRepo;

    private DivisionResponseDto mapToDto(Division division) {
        return new DivisionResponseDto(
                division.div_id(),
                division.code(),
                division.description()
        );
    }

    @Override
    public Mono<DivisionResponseDto> addDivision(DivisionDto dto) {

        Division division = new Division(
                null,
                dto.code(),
                dto.description()
        );

        return divRepo.save(division)
                .map(this::mapToDto);
    }

    @Override
    public Flux<DivisionResponseDto> getAll() {

        return divRepo.findAll()
                .map(this::mapToDto);                  // Division Model → Dto
    }

    @Override
    public Mono<DivisionResponseDto> getById(Long id) {

        return divRepo.findById(id)
                .map(this::mapToDto);                  // Division Model → Dto
    }

    @Override
    public Mono<DivisionResponseDto> update(Long id, DivisionDto dto) {

        return divRepo.findById(id)
                .flatMap(division -> {
                    Division div = new Division(
                           division.div_id(),
                           dto.code(),
                           dto.description()
                    );
                    return divRepo.save(div);
                })
                .map(this::mapToDto);
    }

    @Override
    public Mono<String> delete(Long id) {

        return divRepo.deleteById(id)
                .then(Mono.just("Division with id " + id + " was deleted!"));
    }
}