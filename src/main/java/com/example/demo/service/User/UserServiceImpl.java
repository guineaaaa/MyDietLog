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
        if (user == null || goal == null) throw new IllegalArgumentException("user/goal must not be null");
        if (user.getWeight() == null) throw new IllegalArgumentException("user weight required");
        if (user.getGender() == null) throw new IllegalArgumentException("user gender required");

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

        if (goal.getGoalType() == GoalType.LOSE) {
            double weeks = days / 7.0;
            double maxSafeLoss = weeks * 1.0; // 1주 1kg
            if (kgDelta > maxSafeLoss) {
                // 무리한 감량 경고(필요시 예외/로그)
            }
        }

        Gender gender = user.getGender();
        int baseCalorie = (gender == Gender.MALE)
            ? user.getWeight() * 30
            : user.getWeight() * 25;
        int minCalorie = (gender == Gender.MALE) ? 1500 : 1200;

        int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);
        if (goal.getGoalType() == GoalType.GAIN) {
            baseCalorie += deltaCalorie;
        } else if (goal.getGoalType() == GoalType.LOSE) {
            baseCalorie -= deltaCalorie;
        }
        if (baseCalorie < minCalorie) baseCalorie = minCalorie;

        user.setRecommendedCalorie(baseCalorie);

        int userId = userdao.insertUser(user);
        goal.setUserId(userId);
        goalDao.insertGoal(goal);
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
