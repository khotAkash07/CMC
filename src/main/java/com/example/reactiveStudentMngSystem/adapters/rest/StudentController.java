package com.example.reactiveStudentMngSystem.adapters.rest;

import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import com.example.reactiveStudentMngSystem.domain.dto.StudentDto;
import com.example.reactiveStudentMngSystem.application.port.in.StudentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/student")
@RequiredArgsConstructor
public class StudentController{

    private final StudentUseCase studentUseService;

    //CREATE
    @PostMapping
    public Mono<ResponseEntity<StudentResponseDto>> createStudent(
            @Valid @RequestBody StudentDto dto) {

        return studentUseService.addStudent(dto)
                .map(data -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(data));
    }

    //GET ALL
    @GetMapping
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> getAllStudents() {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService.getAllStudents()));
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<StudentResponseDto>> getById(
            @PathVariable Long id) {

        return studentUseService.getStudentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity
                        .notFound().build());
    }

    //UPDATE
    @PutMapping("/{id}")
    public Mono<ResponseEntity<StudentResponseDto>> updateStudent(
            @Valid @RequestBody StudentDto dto,
            @PathVariable Long id) {

        return studentUseService.updateStudent(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity
                        .notFound().build());
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(
            @PathVariable Long id) {

        return studentUseService.deleteStudent(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity
                        .notFound().build());
    }

    //JOIN: Get All Active Students
    @GetMapping("/join/allStuds")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> getAllStudentsByQuery() {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService.findAllStudentsByQuery()));
    }

    //JOIN: Get By Class AND Division
    @GetMapping("/join/DivClassSpe/{classCode}/{divisionCode}")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> findStudentBasedClassAndDiv(
            @PathVariable String classCode,
            @PathVariable String divisionCode) {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService
                        .findStudentBasedClassAndDiv(classCode, divisionCode)));
    }

    //JOIN: Get Active Students By Class Code
    @GetMapping("/join/classActive/{classCode}")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> findStudentsByClassCode(
            @PathVariable String classCode) {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService
                        .findStudentsByClassCode(classCode)));
    }

    //JOIN: Get Students By Division Code
    @GetMapping("/join/divCode/{divCode}")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> findStudentsByDivisionCode(
            @PathVariable String divCode) {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService
                        .findStudentsByDivisionCode(divCode)));
    }

    //JOIN: Get Deactive Students By Class
    @GetMapping("/join/classDeactive/{classCode}")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> findDeactiveStudentsByClass(
            @PathVariable String classCode) {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService
                        .findDeactiveStudentsByClass(classCode)));
    }

    //JOIN: Get All Deactive Students
    @GetMapping("/join/allDeactiveStuds")
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> getAllDeactiveStudents() {

        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .body(studentUseService
                        .getAllDeactiveStudents()));
    }

    //JOIN: Count Students By Class
    @GetMapping("/join/classCount/{classCode}")
    public Mono<ResponseEntity<Long>> getStudentCountByClass(
            @PathVariable String classCode) {

        return studentUseService.getStudentCountByClass(classCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity
                        .notFound().build());
    }
}