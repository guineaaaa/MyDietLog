package com.example.demo.service.Goal;

import com.example.demo.model.Goal;

public interface GoalService {
	Goal findLatestGoalByUserId(int userId);
	int calcGoalProgressPercent(Goal goal);
	void updateGoal(Goal goal);
}
