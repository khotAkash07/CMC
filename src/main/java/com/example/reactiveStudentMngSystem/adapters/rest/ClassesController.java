package com.example.reactiveStudentMngSystem.adapters.rest;

import com.example.reactiveStudentMngSystem.domain.dto.ClassDto;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.ClassResponseDto;
import com.example.reactiveStudentMngSystem.application.service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/class")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @PostMapping
    public Mono<ResponseEntity<ClassResponseDto>> create(
            @Valid @RequestBody ClassDto dto) {

        return classesService.addClass(dto)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @GetMapping
    public Flux<ClassResponseDto> getAll() {
        return classesService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClassResponseDto>> getById(@PathVariable Long id) {

        return classesService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ClassResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ClassDto dto) {

        return classesService.update(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {

        return classesService.delete(id)
                .map(ResponseEntity::ok);
    }
}