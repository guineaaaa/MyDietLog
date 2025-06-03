package com.example.demo.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoalDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Goal;
import com.example.demo.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userdao;
    @Autowired private GoalDao goalDao;

    @Override
    public void registerUserWithGoal(User user, Goal goal) {
        // 1. 목표 일수 계산 (끝일 포함)
        long days = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate()) + 1;
        if (days < 1) days = 1;

        // 2. 증량/감량 변화량 계산 (방향성 체크, Enum 타입 기준)
        double kgDelta = 0;
        String goalType = goal.getGoalType().name(); // Enum 타입일 때
        
        if ("GAIN".equals(goalType) && goal.getGoalWeight() > user.getWeight()) {
            kgDelta = goal.getGoalWeight() - user.getWeight();
        } else if ("LOSE".equals(goalType) && goal.getGoalWeight() < user.getWeight()) {
            kgDelta = user.getWeight() - goal.getGoalWeight();
        }
        // 감량인데 목표가 더 크거나, 증량인데 목표가 더 작으면 kgDelta=0

        int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);

        // 3. 기본 권장 칼로리 (성별)
        int baseCalorie;
        if ("MALE".equalsIgnoreCase(user.getGender())) {
            baseCalorie = user.getWeight() * 30;
        } else {
            baseCalorie = user.getWeight() * 25;
        }

        // 4. 증/감 적용 (증량이면 +, 감량이면 -)
        if ("GAIN".equals(goalType)) {
            baseCalorie += deltaCalorie;
        } else if ("LOSE".equals(goalType)) {
            baseCalorie -= deltaCalorie;
        }
        if (baseCalorie < 1000) baseCalorie = 1000;

        user.setRecommendedCalorie(baseCalorie);

        // 5. 저장
        int userId = userdao.insertUser(user); // User 등록(PK 반환)
        goal.setUserId(userId); // Goal 객체에 PK 세팅
        goalDao.insertGoal(goal); // Goal 등록 (User의 PK로 연결)
    }

    @Override
    public User login(String loginId, String password) {
        return userdao.findByLoginIdAndPassword(loginId, password);
    }
}
