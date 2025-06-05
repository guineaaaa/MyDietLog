package com.example.demo.dao;

import com.example.demo.model.DietLog;
import com.example.demo.model.enums.MealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DietLogDaoImpl implements DietLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(DietLog dietLog) {
        String sql = "INSERT INTO DietLog (userId, log_date, meal_type, food_name, calorie, reg_time) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                dietLog.getUserId(),
                dietLog.getLogDate(),
                dietLog.getMealType().name(), // Enum → String
                dietLog.getFoodName(),
                dietLog.getCalorie(),
                dietLog.getRegTime() != null ? dietLog.getRegTime() : LocalDateTime.now()
        );
    }

    @Override
    public List<DietLog> findByUserIdAndDate(int userId, String logDate) {
        String sql = "SELECT * FROM DietLog WHERE userId = ? AND log_date = ?";
        return jdbcTemplate.query(sql, new Object[]{userId, logDate}, new DietLogRowMapper());
    }

    // RowMapper 내부 클래스
    private static class DietLogRowMapper implements RowMapper<DietLog> {
        @Override
        public DietLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            DietLog log = new DietLog();
            log.setId(rs.getInt("id"));
            log.setUserId(rs.getInt("userId"));
            log.setLogDate(rs.getDate("log_date").toLocalDate());
            log.setMealType(MealType.valueOf(rs.getString("meal_type"))); // String → Enum
            log.setFoodName(rs.getString("food_name"));
            log.setCalorie(rs.getInt("calorie"));
            log.setRegTime(rs.getTimestamp("reg_time").toLocalDateTime());
            return log;
        }
    }
    
    // Streak 계산용 쿼리: 기록이 있는 날짜를 역순으로 모두 불러오기
    public List<LocalDate> findRecordedDates(int userId) {
        String sql = """
            SELECT log_date FROM (
                SELECT log_date FROM DietLog WHERE userId=?
                UNION
                SELECT log_date FROM ExerciseLog WHERE userId=?
            ) t
            GROUP BY log_date
            ORDER BY log_date DESC
        """;
        return jdbcTemplate.query(sql, new Object[]{userId, userId}, (rs, rowNum) -> rs.getDate("log_date").toLocalDate());
    }
}
