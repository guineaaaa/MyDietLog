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
    private int calculateRecommendedCalorie(User user,Goal goal) {
    	long days = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate()) + 1;
        if (days < 1) days = 1;
        
        double kgDelta = 0;
        switch (goal.getGoalType()) {
            case GAIN:
                if (goal.getGoalWeight() > user.getWeight())
                    kgDelta = goal.getGoalWeight() - user.getWeight();
                break;
            case LOSE:
                if (goal.getGoalWeight() < user.getWeight())
                    kgDelta = user.getWeight() - goal.getGoalWeight();
                break;
        }
        // 목표 안전성 체크 (주당 1kg 감량, 0.5kg 증량 제한)
        double weeks = days / 7.0;
        if (goal.getGoalType() == GoalType.LOSE && kgDelta / weeks > 1.0) {
            throw new IllegalArgumentException("건강을 위해 감량 목표는 1주 1kg 이하로 설정하세요.");
        }
        if (goal.getGoalType() == GoalType.GAIN && kgDelta / weeks > 0.5) {
            throw new IllegalArgumentException("건강을 위해 증량 목표는 1주 0.5kg 이하로 설정하세요.");
        }
        // 표준 BMR 공식 적용
        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }
        
        // 활동량 없는 생활: BMR * 1.2
        double recommendedCalorie=bmr*1.2;
        
        // 감량/증량 목표 반영
        int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);
        if (goal.getGoalType() == GoalType.GAIN) {
            recommendedCalorie += Math.min(deltaCalorie, 500);
        } else if (goal.getGoalType() == GoalType.LOSE) {
            recommendedCalorie -= Math.min(deltaCalorie, 500);
        }

        // 하한선 적용
        int minCalorie = (user.getGender() == Gender.MALE) ? 1500 : 1200;
        if (recommendedCalorie < minCalorie) recommendedCalorie = minCalorie;

        return (int)Math.round(recommendedCalorie);
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
