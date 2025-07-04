package com.my131.edu_system.repository;

import com.my131.edu_system.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Teacher> mapper = (resultSet, rowNum) ->
            Teacher.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();

    public List<Teacher> findAll() {
        return jdbcTemplate.query("SELECT * FROM teacher ORDER BY name", mapper);
    }

    public Teacher findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE id = ?", mapper, id);
    }

    //int인 이유 : int 는 업데이트된 행의 갯수가 return됨
    public int save(Teacher teacher) {
        return jdbcTemplate.update(
                "INSERT INTO teacher (name) VALUES (?)", teacher.getName()
        );
    }

    public int update(Teacher teacher) {
        return jdbcTemplate.update(
                "UPDATE teacher SET name = ? WHERE id = ?", teacher.getName(), teacher.getId()
        );
    }
}
