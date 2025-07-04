package com.my131.edu_system.repository;

import com.my131.edu_system.model.Student;
import com.my131.edu_system.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public int save(Student student) {
        return jdbcTemplate.update(
                "INSERT INTO Student (name) VALUES (?)", student.getName()
        );
    }
}
