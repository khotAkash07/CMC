package com.example.reactiveStudentMngSystem.infrastructure.persistence.adapter;

import com.example.reactiveStudentMngSystem.application.port.out.StudentRepositoryPort;
import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import com.example.reactiveStudentMngSystem.domain.model.Student;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.entity.StudentEntity;
import com.example.reactiveStudentMngSystem.infrastructure.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentRepositoryPort {

    private final StudentRepository studentRepository;
    private final StudentQueryAdapter studentQueryAdapter;

    @Override
    public Mono<Student> save(Student student) {
        return studentRepository.save(toEntity(student))   // Model → Entity
                .map(this::toModel);                       // Entity → Model
    }

    @Override
    public Flux<Student> findByActiveTrue() {
        return studentRepository.findByActiveTrue()
                .map(this::toModel);                       // Entity → Model
    }

    @Override
    public Mono<Student> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::toModel);                       // Entity → Model
    }

    @Override
    public Flux<StudentResponseDto> findAllStudentsByQuery() {
        return studentQueryAdapter.findAllStudentsByQuery();
    }

    @Override
    public Flux<StudentResponseDto> findStudentBasedClassAndDiv(String classCode, String divisionCode) {
        return studentQueryAdapter.findStudentBasedClassAndDiv(classCode, divisionCode);
    }

    @Override
    public Flux<StudentResponseDto> findStudentsByClassCode(String classCode) {
        return studentQueryAdapter.findStudentsByClassCode(classCode);
    }

    @Override
    public Flux<StudentResponseDto> findStudentsByDivisionCode(String divCode) {
        return studentQueryAdapter.findStudentsByDivisionCode(divCode);
    }

    @Override
    public Flux<StudentResponseDto> findDeactiveStudentsByClass(String classCode) {
        return studentQueryAdapter.findDeactiveStudentsByClass(classCode);
    }

    @Override
    public Flux<StudentResponseDto> getAllDeactiveStudents() {
        return studentQueryAdapter.getAllDeactiveStudents();
    }

    @Override
    public Mono<Long> getStudentCountByClass(String classCode) {
        return studentQueryAdapter.getStudentCountByClass(classCode);
    }

    // Mapper Methods

    private StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();
        entity.setId(student.id());
        entity.setName(student.name());
        entity.setEmail(student.email());
        entity.setPhone(student.phone());
        entity.setClassId(student.classId());
        entity.setDivisionId(student.divisionId());
        entity.setActive(student.active());
        entity.setCreatedDate(student.createdDate());
        return entity;
    }

    private Student toModel(StudentEntity entity) {
        return new Student(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getClassId(),
                entity.getDivisionId(),
                entity.getActive(),
                entity.getCreatedDate()
        );
    }


}

