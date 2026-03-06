package com.example.reactiveStudentMngSystem.domain.dto.ResponseDto;

import java.time.LocalDateTime;

public record StudentResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDateTime createdDate,
        Boolean active,
        String classCode,
        String divisionCode
) {}