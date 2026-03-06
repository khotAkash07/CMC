package com.example.reactiveStudentMngSystem.domain.dto.ResponseDto;

public record ClassResponseDto(
        Long class_id,
        String code,
        String description
) {}