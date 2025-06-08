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
    
    // 권장 칼로리 산출만 담당
    // 재사용성 증가, 의존성 없는 도구로 쓰기 위해 static으로 선언
    // GoalService에서 이후 목표 수정 시 이 함수를 다시 호출한다.
    public static int calculateRecommendedCalorie(User user, Goal goal) {
        long days = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate()) + 1;
        if (days < 1) days = 1;

        double kgDelta = Math.abs(goal.getGoalWeight());
        if (kgDelta <= 0) throw new IllegalArgumentException("감량/증량 kg 수가 0 이상이어야 합니다.");

        double weeks = days / 7.0;
        if (goal.getGoalType() == GoalType.LOSE && kgDelta / weeks > 1.0) {
            throw new IllegalArgumentException("건강을 위해 감량 목표는 1주 1kg 이하로 설정하세요.");
        }
        if (goal.getGoalType() == GoalType.GAIN && kgDelta / weeks > 0.5) {
            throw new IllegalArgumentException("건강을 위해 증량 목표는 1주 0.5kg 이하로 설정하세요.");
        }

        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }

        double activityLevel = (user.getActivityLevel() != null) ? user.getActivityLevel() : 1.2;
        double recommendedCalorie = bmr * activityLevel;

        int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);
        if (goal.getGoalType() == GoalType.GAIN) {
            recommendedCalorie += Math.min(deltaCalorie, 500);
        } else if (goal.getGoalType() == GoalType.LOSE) {
            recommendedCalorie -= Math.min(deltaCalorie, 500);
        }

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
