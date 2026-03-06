package com.example.reactiveStudentMngSystem.application.service;

import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import com.example.reactiveStudentMngSystem.domain.dto.StudentDto;
import com.example.reactiveStudentMngSystem.application.port.in.StudentUseCase;
import com.example.reactiveStudentMngSystem.application.port.out.ClassesRepositoryPort;
import com.example.reactiveStudentMngSystem.application.port.out.DivisionRepositoryPort;
import com.example.reactiveStudentMngSystem.application.port.out.StudentRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.model.Classes;
import com.example.reactiveStudentMngSystem.domain.model.Division;
import com.example.reactiveStudentMngSystem.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentUseCase {

    private final StudentRepositoryPort studentRepository;
    private final ClassesRepositoryPort classRepository;
    private final DivisionRepositoryPort divisionRepo;
    private final StudentRepositoryPort studentRepositoryPort;

    // ── Map domain Model → ResponseDto ────────────────────────────────────────
    private StudentResponseDto mapToDto(Student student,
                                        String classCode,
                                        String divisionCode) {
        return new StudentResponseDto(
                student.id(),
                student.name(),
                student.email(),
                student.phone(),
                student.createdDate(),
                student.active(),
                classCode,
                divisionCode
        );
    }

    @Override
    public Mono<StudentResponseDto> addStudent(StudentDto dto) {

        return classRepository.findByCode(dto.classCode())
                .zipWith(divisionRepo.findByCode(dto.divisionCode()))
                .flatMap(tuple -> {

                    Classes classes = tuple.getT1();
                    Division division = tuple.getT2();

                    Student student = new Student(
                            null,
                            dto.name(),
                            dto.email(),
                            dto.phone(),
                            classes.class_id(),
                            division.div_id(),
                            true,
                            LocalDateTime.now()
                    );

                    return studentRepository.save(student)
                            .map(saved ->
                                    mapToDto(saved,
                                            classes.code(),
                                            division.code()));
                });
    }

    @Override
    public Flux<StudentResponseDto> getAllStudents() {

        return studentRepository.findByActiveTrue()
                .flatMap(student ->

                        classRepository.findById(student.classId())
                                .zipWith(divisionRepo.findById(student.divisionId()))
                                .map(tuple ->
                                        mapToDto(student,
                                                tuple.getT1().code(),
                                                tuple.getT2().code()))
                );
    }

    @Override
    public Mono<StudentResponseDto> getStudentById(Long id) {

        return studentRepository.findById(id)
                .flatMap(student ->
                        classRepository.findById(student.classId())
                                .zipWith(divisionRepo.findById(student.divisionId()))
                                .map(tuple ->
                                        mapToDto(student,
                                                tuple.getT1().code(),
                                                tuple.getT2().code()))
                );
    }

    @Override
    public Mono<StudentResponseDto> updateStudent(Long id, StudentDto dto) {

        return studentRepository.findById(id)
                .zipWith(classRepository.findByCode(dto.classCode()))
                .zipWith(divisionRepo.findByCode(dto.divisionCode()))
                .flatMap(tuple -> {

                    Student existing = tuple.getT1().getT1();
                    Classes newClass = tuple.getT1().getT2();
                    Division newDivision = tuple.getT2();

                    Student updatedStudent = new Student(
                          existing.id(),
                          dto.name(),
                            dto.email(),
                            dto.phone(),
                            newClass.class_id(),
                            newDivision.div_id(),
                            existing.active(),
                            existing.createdDate()
                    );

                    return studentRepository.save(updatedStudent)
                            .map(saved -> mapToDto(saved,
                                    newClass.code(),
                                    newDivision.code()));
                });
    }

    @Override
    public Mono<String> deleteStudent(Long id) {

        return studentRepository.findById(id)
                .flatMap(student -> {
                    Student deleteStud = new Student(
                            student.id(),
                            student.name(),
                            student.email(),
                            student.phone(),
                            student.classId(),
                            student.divisionId(),
                            false,
                            student.createdDate()
                    );
                    return studentRepository.save(deleteStud);
                })
                .thenReturn("Student deleted successfully");
    }

    //Method created manually

    @Override
    public Flux<StudentResponseDto> findAllStudentsByQuery() {
        return studentRepositoryPort.findAllStudentsByQuery();
    }

    @Override
    public Flux<StudentResponseDto> findStudentBasedClassAndDiv(String classCode, String divisionCode) {
        return studentRepositoryPort.findStudentBasedClassAndDiv(classCode, divisionCode);
    }

    @Override
    public Flux<StudentResponseDto> findStudentsByClassCode(String classCode) {
        return studentRepositoryPort.findStudentsByClassCode(classCode);
    }

    @Override
    public Flux<StudentResponseDto> findStudentsByDivisionCode(String divCode) {
        return studentRepositoryPort.findStudentsByDivisionCode(divCode);
    }

    @Override
    public Flux<StudentResponseDto> findDeactiveStudentsByClass(String classCode) {
        return studentRepositoryPort.findDeactiveStudentsByClass(classCode);
    }

    @Override
    public Flux<StudentResponseDto> getAllDeactiveStudents() {
        return studentRepositoryPort.getAllDeactiveStudents();
    }

    @Override
    public Mono<Long> getStudentCountByClass(String classCode) {
        return studentRepositoryPort.getStudentCountByClass(classCode);
    }
}