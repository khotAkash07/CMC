package com.example.reactiveStudentMngSystem.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("students")
public class StudentEntity {

    @Id
    private Long id;
    private String name;

    private String email;
    
    private String phone;

    @Column("class_id")
    private Long classId;

    @Column("div_id")
    private Long divisionId;

    private Boolean active = true;

    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;
}