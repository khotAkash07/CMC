package com.example.reactiveStudentMngSystem.application.port.in;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import com.example.reactiveStudentMngSystem.domain.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentUseCase {
    Mono<StudentResponseDto> addStudent(StudentDto dto);
    Flux<StudentResponseDto> getAllStudents();
    Mono<StudentResponseDto> getStudentById(Long id);
    Mono<String> deleteStudent(Long id);
    Mono<StudentResponseDto> updateStudent(Long id, StudentDto dto);

    //Task
    Flux<StudentResponseDto> findAllStudentsByQuery();
    Flux<StudentResponseDto> findStudentBasedClassAndDiv(String classCode, String divisionCode);
    Flux<StudentResponseDto> findStudentsByClassCode(String classCode);
    Flux<StudentResponseDto> findStudentsByDivisionCode(String divCode);
    Flux<StudentResponseDto> findDeactiveStudentsByClass(String classCode);
    Flux<StudentResponseDto> getAllDeactiveStudents();
    Mono<Long> getStudentCountByClass(String classCode);
}