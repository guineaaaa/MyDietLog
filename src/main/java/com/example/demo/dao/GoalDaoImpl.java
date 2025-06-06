package com.example.demo.dao;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Goal;

@Repository
public class GoalDaoImpl implements GoalDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Override
    public void insertGoal(Goal goal) {
        String sql = "INSERT INTO Goal (userId, goal_weight, goal_type, memo, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            goal.getUserId(),
            goal.getGoalWeight(),
            goal.getGoalType().name(), // 자바에서 Enum 타입을 다룰 때 해당 Enum 상수의 "이름" 문자열을 반환하는 메소드
            goal.getMemo(),
            goal.getStartDate(),
            goal.getEndDate()
        );
    }
    
    @Override
    public Goal findLatestGoalByUserId(int userId) {
        String sql = "SELECT * FROM Goal WHERE userId = ? ORDER BY end_date DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
                Goal goal = new Goal();
                goal.setId(rs.getInt("id"));
                goal.setUserId(rs.getInt("userId"));
                goal.setGoalWeight(rs.getDouble("goal_weight"));
                goal.setGoalType(com.example.demo.model.enums.GoalType.valueOf(rs.getString("goal_type")));
                goal.setMemo(rs.getString("memo"));
                goal.setStartDate(rs.getDate("start_date").toLocalDate());
                goal.setEndDate(rs.getDate("end_date").toLocalDate());
                return goal;
            });
        } catch (Exception e) {
            return null; // 목표가 없으면 null 반환
        }
    }
    @Override
    public void update(Goal goal) {
        String sql = "UPDATE Goal SET goal_weight=?, goal_type=?, memo=?, start_date=?, end_date=? WHERE id=?";
        jdbcTemplate.update(sql,
            goal.getGoalWeight(),
            goal.getGoalType().name(),
            goal.getMemo(),
            goal.getStartDate(),
            goal.getEndDate(),
            goal.getId()
        );
    }
}
