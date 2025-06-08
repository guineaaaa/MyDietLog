package com.example.demo.service.Goal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoalDao.GoalDao;
import com.example.demo.dao.User.UserDao;
import com.example.demo.model.Goal;
import com.example.demo.model.User;
import com.example.demo.service.User.UserService;
import com.example.demo.service.User.UserServiceImpl;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired private GoalDao goalDao;
    @Autowired private UserDao userDao; 
    @Autowired private UserService userService;
    
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
        // 1. 목표 업데이트
        goalDao.update(goal);

        // 2. 사용자 정보 로드
        User user = userDao.findById(goal.getUserId());
        if(user == null) throw new IllegalArgumentException("존재하지 않는 사용자");

        // 3. 권장 칼로리 재계산 (UserService의 private 메서드를 복사하거나 public static으로 빼도 됨)
        int newCalorie = UserServiceImpl.calculateRecommendedCalorie(user, goal);
        // 4. User DB 업데이트
        userDao.updateRecommendedCalorie(user.getId(), newCalorie);
    }
}
