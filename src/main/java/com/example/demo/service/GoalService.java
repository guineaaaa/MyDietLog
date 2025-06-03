package com.example.demo.service;

import com.example.demo.model.Goal;

public interface GoalService {
	Goal findLatestGoalByUserId(int userId);
	int calcGoalProgressPercent(Goal goal);
}
