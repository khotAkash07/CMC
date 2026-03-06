package com.example.reactiveStudentMngSystem.adapters.rest;

import com.example.reactiveStudentMngSystem.domain.dto.DivisionDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.DivisionResponseDto;
import com.example.reactiveStudentMngSystem.application.service.DivisionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/division")
@RequiredArgsConstructor
public class DivisionController {

    private final DivisionService divisionService;

    @PostMapping
    public Mono<ResponseEntity<DivisionResponseDto>> create(
            @Valid @RequestBody DivisionDto dto) {

        return divisionService.addDivision(dto)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @GetMapping
    public Flux<DivisionResponseDto> getAll() {
        return divisionService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<DivisionResponseDto>> getById(@PathVariable Long id) {

        return divisionService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<DivisionResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody DivisionDto dto) {

        return divisionService.update(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {

        return divisionService.delete(id)
                .map(msg -> ResponseEntity.ok(msg));
    }
}