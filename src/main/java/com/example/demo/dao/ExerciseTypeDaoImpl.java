package com.example.demo.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ExerciseType;

@Repository
public class ExerciseTypeDaoImpl implements ExerciseTypeDao {
    @Autowired private JdbcTemplate jdbcTemplate;
    @Override
    public List<ExerciseType> findAll() {
        String sql = "SELECT * FROM ExerciseType";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ExerciseType type = new ExerciseType();
            type.setId(rs.getInt("id"));
            type.setTypeCode(rs.getString("type_code"));
            type.setTypeName(rs.getString("type_name"));
            return type;
        });
    }
}
