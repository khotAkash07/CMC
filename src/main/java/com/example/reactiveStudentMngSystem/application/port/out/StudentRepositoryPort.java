package com.example.reactiveStudentMngSystem.application.port.out;

import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import com.example.reactiveStudentMngSystem.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<Student> save(Student student);
    Flux<Student> findByActiveTrue();
    Mono<Student> findById(Long id);

    //Join Example Methods
    Flux<StudentResponseDto> findAllStudentsByQuery();
    Flux<StudentResponseDto> findStudentBasedClassAndDiv(String classCode, String divisionCode);
    Flux<StudentResponseDto> findStudentsByClassCode(String classCode);
    Flux<StudentResponseDto> findStudentsByDivisionCode(String divCode);
    Flux<StudentResponseDto> findDeactiveStudentsByClass(String classCode);
    Flux<StudentResponseDto> getAllDeactiveStudents();
    Mono<Long> getStudentCountByClass(String classCode);
}