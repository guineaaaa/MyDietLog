package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoalDao;
import com.example.demo.model.Goal;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired private GoalDao goalDao;
    @Override
    public Goal findLatestGoalByUserId(int userId) {
        return goalDao.findLatestGoalByUserId(userId);
    }
    @Override
    public int calcGoalProgressPercent(Goal goal) {
        if (goal == null) return 0;
        LocalDate start = goal.getStartDate();
        LocalDate end = goal.getEndDate();
        long totalDays = ChronoUnit.DAYS.between(start, end) + 1;
        long passedDays = ChronoUnit.DAYS.between(start, LocalDate.now()) + 1;
        int percent = (int) Math.round((Math.min(passedDays, totalDays) * 100.0) / totalDays);
        if (percent < 0) percent = 0;
        if (percent > 100) percent = 100;
        return percent;
    }
    
    @Override
    public void updateGoal(Goal goal) {
    	goalDao.update(goal);
    }
}
