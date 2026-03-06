package com.example.reactiveStudentMngSystem.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("classes")
@Data
public class ClassesEntity {

    @Id
    private Long class_id;
    private String code;
    private String description;
}