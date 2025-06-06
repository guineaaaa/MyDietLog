package com.example.demo.dao.GoalDao;
import com.example.demo.model.Goal;

public interface GoalDao {
	void insertGoal(Goal goal);
	Goal findLatestGoalByUserId(int userId);
	void update(Goal goal);
}
