package com.example.reactiveStudentMngSystem.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("divisions")
@Data
public class DivisionEntity {

    @Id
    private Long div_id;
    private String code;
    private String description;
}