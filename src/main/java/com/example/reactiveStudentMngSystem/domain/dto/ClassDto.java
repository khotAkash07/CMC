package com.example.reactiveStudentMngSystem.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ClassDto(

        @NotBlank(message = "Class code is required")
        String code,

        @NotBlank(message = "Class description is required")
        String description
) {}