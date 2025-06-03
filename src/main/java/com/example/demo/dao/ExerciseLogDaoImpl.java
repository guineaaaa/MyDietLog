package com.example.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ExerciseLog;

@Repository
public class ExerciseLogDaoImpl implements ExerciseLogDao {
    @Autowired private JdbcTemplate jdbcTemplate;
    @Override
    public void insert(ExerciseLog log) {
        String sql = "INSERT INTO ExerciseLog (userId, exercise_type_id, exercise_name, calorie_burned, duration, log_date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, log.getUserId(), log.getExerciseTypeId(), log.getExerciseName(), log.getCalorieBurned(), log.getDuration(), log.getLogDate());
    }
}
