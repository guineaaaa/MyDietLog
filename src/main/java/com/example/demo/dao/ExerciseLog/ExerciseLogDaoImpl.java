package com.example.demo.dao.ExerciseLog;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    
    @Override
    public List<ExerciseLog> findByUserIdAndDate(int userId, String logDate) {
        String sql = "SELECT * FROM ExerciseLog WHERE userId = ? AND log_date = ?";
        return jdbcTemplate.query(sql, new Object[]{userId, Date.valueOf(logDate)}, (rs, rowNum) -> {
            ExerciseLog log = new ExerciseLog();
            log.setId(rs.getInt("id"));
            log.setUserId(rs.getInt("userId"));
            log.setExerciseTypeId(rs.getInt("exercise_type_id"));
            log.setExerciseName(rs.getString("exercise_name"));
            log.setCalorieBurned(rs.getInt("calorie_burned"));
            log.setDuration(rs.getObject("duration") != null ? rs.getInt("duration") : null);
            log.setLogDate(rs.getDate("log_date").toLocalDate());
            log.setRegTime(rs.getTimestamp("reg_time") != null ? rs.getTimestamp("reg_time").toLocalDateTime() : null);
            return log;
        });
    }

    // 기간별 운동 기록 조회
    @Override
    public List<ExerciseLog> findByUserIdAndDateRange(int userId, LocalDate start, LocalDate end) {
        String sql = "SELECT * FROM ExerciseLog WHERE userId = ? AND log_date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql,
                new Object[]{userId, Date.valueOf(start), Date.valueOf(end)},
                (rs, rowNum) -> {
                    ExerciseLog log = new ExerciseLog();
                    log.setId(rs.getInt("id"));
                    log.setUserId(rs.getInt("userId"));
                    log.setExerciseTypeId(rs.getInt("exercise_type_id"));
                    log.setExerciseName(rs.getString("exercise_name"));
                    log.setCalorieBurned(rs.getInt("calorie_burned"));
                    log.setDuration(rs.getObject("duration") != null ? rs.getInt("duration") : null);
                    log.setLogDate(rs.getDate("log_date").toLocalDate());
                    log.setRegTime(rs.getTimestamp("reg_time") != null ? rs.getTimestamp("reg_time").toLocalDateTime() : null);
                    return log;
                }
        );
    }
    
   
}
