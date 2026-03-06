package com.example.reactiveStudentMngSystem.domain.model;

import java.time.LocalDateTime;

public record Student(
        Long id,
        String name,
        String email,
        String phone,
        Long classId,
        Long divisionId,
        Boolean active,
        LocalDateTime createdDate
){}