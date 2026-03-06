package com.example.reactiveStudentMngSystem.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentDto(

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @Size(min = 10, max = 15, message = "Phone must be 10-15 digits")
        @NotBlank(message = "Phone is required")
        String phone,

        @NotNull(message = "Class is required")
        String classCode,

        @NotNull(message = "Division is required")
        String divisionCode
) {}