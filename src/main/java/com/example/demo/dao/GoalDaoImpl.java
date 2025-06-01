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
            goal.getGoalType(),
            goal.getMemo(),
            goal.getStartDate(),
            goal.getEndDate()
        );
    }
}
