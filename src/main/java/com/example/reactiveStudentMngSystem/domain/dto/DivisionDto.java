package com.example.reactiveStudentMngSystem.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DivisionDto(

        @NotBlank(message = "Enter division code")
        String code,

        @NotBlank(message = "Enter division description")
        String description
) {}