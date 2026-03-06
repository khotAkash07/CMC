package com.example.reactiveStudentMngSystem.infrastructure.persistence.adapter;

import com.example.reactiveStudentMngSystem.domain.dto.ResponseDto.StudentResponseDto;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StudentQueryAdapter {

    private final DatabaseClient databaseClient;

    public Flux<StudentResponseDto> findAllStudentsByQuery(){

        String query = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c ON s.class_id = c.class_id
                join divisions d ON s.div_id   = d.div_id
                where s.active = true
                ORDER BY  s.id
                """;

        return databaseClient.sql(query)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Flux<StudentResponseDto> findStudentBasedClassAndDiv(String classCode, String divisionCode){
        String query = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c ON s.class_id = c.class_id
                join divisions d ON s.div_id = d.div_id
                where s.active = true
                and c.code = :clCode
                and d.code = :divCode
                """;

        return databaseClient.sql(query)
                .bind("clCode", classCode)
                .bind("divCode", divisionCode)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Flux<StudentResponseDto> findStudentsByClassCode(String classCode) {

        String sql = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c on s.class_id = c.class_id
                join divisions d on s.div_id = d.div_id
                where s.active  = true
                and c.code = :classCode
                order by s.id
                """;

        return databaseClient.sql(sql)
                .bind("classCode", classCode)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Flux<StudentResponseDto> findStudentsByDivisionCode(String divCode) {

        String sql = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c on s.class_id = c.class_id
                join divisions d on s.div_id = d.div_id
                where s.active  = true
                and d.code = :divisionCode
                order by s.id
                """;

        return databaseClient.sql(sql)
                .bind("divisionCode", divCode)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Flux<StudentResponseDto> findDeactiveStudentsByClass(String classCode) {

        String sql = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c on s.class_id = c.class_id
                join divisions d on s.div_id = d.div_id
                where s.active  = false
                and c.code = :classCode
                order by s.id
                """;

        return databaseClient.sql(sql)
                .bind("classCode", classCode)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Flux<StudentResponseDto> getAllDeactiveStudents() {
        String query = """
                select
                s.id,
                s.name,
                s.email,
                s.phone,
                s.created_date,
                s.active,
                c.code as class_code,
                d.code as division_code
                from students s
                join classes c on s.class_id = c.class_id
                join divisions d on s.div_id = d.div_id
                where s.active  = false
        """;

        return databaseClient.sql(query)
                .map(row -> mapRowToDto((Row) row))
                .all();
    }

    public Mono<Long> getStudentCountByClass(String classCode) {
        String query = """
                select count(s) as total
                from students s
                join classes c on s.class_id = c.class_id
                where s.active  = true
                and c.code = :classCode
                """;

        return databaseClient.sql(query)
                .bind("classCode", classCode)
                .map(row -> row.get("total",  Long.class))
                .one();
    }

    private StudentResponseDto mapRowToDto(io.r2dbc.spi.Row row) {
        return new StudentResponseDto(
                row.get("id", Long.class),
                row.get("name", String.class),
                row.get("email", String.class),
                row.get("phone", String.class),
                row.get("created_date", LocalDateTime.class),
                row.get("active", Boolean.class),
                row.get("class_code", String.class),
                row.get("division_code", String.class)
        );
    }
}
