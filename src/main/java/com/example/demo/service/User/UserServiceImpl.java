package com.example.demo.service.User;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoalDao.GoalDao;
import com.example.demo.dao.User.UserDao;
import com.example.demo.model.Goal;
import com.example.demo.model.User;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.GoalType;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userdao;
    @Autowired private GoalDao goalDao;

    @Override
    public void registerUserWithGoal(User user, Goal goal) {
        // 입력 검증
        if (user == null || goal == null) throw new IllegalArgumentException("user/goal must not be null");
        if (user.getWeight() == null || user.getHeight() == null || user.getAge() == null)
            throw new IllegalArgumentException("user weight, height, age required");
        if (user.getGender() == null)
            throw new IllegalArgumentException("user gender required");

        // 권장칼로리 계산
        int recommendedCalorie = calculateRecommendedCalorie(user, goal);
        user.setRecommendedCalorie(recommendedCalorie);

        int userId = userdao.insertUser(user);
        goal.setUserId(userId);
        goalDao.insertGoal(goal);
    }
    
    // 권장 칼로리 산출만 담당하는 private 메소드
    private int calculateRecommendedCalorie(User user, Goal goal) {
        long days = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate()) + 1;
        if (days < 1) days = 1;

        // [1] 감량/증량 목표(kg)
        double kgDelta = Math.abs(goal.getGoalWeight()); // 입력값이 음수면 Math.abs로 처리 (일관성 위해)
        if (kgDelta <= 0) throw new IllegalArgumentException("감량/증량 kg 수가 0 이상이어야 합니다.");

        // [2] 증/감량 타입에 따라 계산
        double weeks = days / 7.0;
        if (goal.getGoalType() == GoalType.LOSE && kgDelta / weeks > 1.0) {
            throw new IllegalArgumentException("건강을 위해 감량 목표는 1주 1kg 이하로 설정하세요.");
        }
        if (goal.getGoalType() == GoalType.GAIN && kgDelta / weeks > 0.5) {
            throw new IllegalArgumentException("건강을 위해 증량 목표는 1주 0.5kg 이하로 설정하세요.");
        }

        // [3] 표준 BMR 공식 적용
        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }
        double recommendedCalorie = bmr * 1.2;

        // [4] 감량/증량 목표 반영
        int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);
        if (goal.getGoalType() == GoalType.GAIN) {
            recommendedCalorie += Math.min(deltaCalorie, 500);
        } else if (goal.getGoalType() == GoalType.LOSE) {
            recommendedCalorie -= Math.min(deltaCalorie, 500);
        }

        // [5] 하한선 적용
        int minCalorie = (user.getGender() == Gender.MALE) ? 2200 : 1600;
        if (recommendedCalorie < minCalorie) recommendedCalorie = minCalorie;

        return (int) Math.round(recommendedCalorie);
    }


    @Override
    public User login(String loginId, String password) {
        return userdao.findByLoginIdAndPassword(loginId, password);
    }

    @Override
    public User findById(int userId) {
        return userdao.findById(userId);
    }
}
