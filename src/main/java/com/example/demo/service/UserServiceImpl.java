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
    	// 1. 목표일수 계산
    	long days = ChronoUnit.DAYS.between(goal.getStartDate(), goal.getEndDate()) + 1;
    	if (days < 1) days = 1;

    	// 2. 증/감량 변화량 계산
    	double kgDelta = 0;
    	String goalType = goal.getGoalType().name();
    	if ("GAIN".equals(goalType) && goal.getGoalWeight() > user.getWeight()) {
    	    kgDelta = goal.getGoalWeight() - user.getWeight();
    	} else if ("LOSE".equals(goalType) && goal.getGoalWeight() < user.getWeight()) {
    	    kgDelta = user.getWeight() - goal.getGoalWeight();
    	}

    	// 3. 건강한 감량 속도 체크 (감량일 때만)
    	if ("LOSE".equals(goalType)) {
    	    double weeks = days / 7.0;
    	    double maxSafeLoss = weeks * 1.0; // 1주 1kg
    	    if (kgDelta > maxSafeLoss) {
    	        // ★ 경고 메시지: 너무 무리한 감량 목표입니다!
    	        // return or 알림 제공
    	    }
    	}

    	// 4. 기본 권장 칼로리
    	int baseCalorie = ("MALE".equalsIgnoreCase(user.getGender()))
    	    ? user.getWeight() * 30
    	    : user.getWeight() * 25;

    	// 5. 증/감 적용
    	int deltaCalorie = (int) Math.round((kgDelta * 7700) / days);
    	if ("GAIN".equals(goalType)) {
    	    baseCalorie += deltaCalorie;
    	} else if ("LOSE".equals(goalType)) {
    	    baseCalorie -= deltaCalorie;
    	}

    	// 6. 하한선 적용 (여성 1200, 남성 1500)
    	int minCalorie = ("MALE".equalsIgnoreCase(user.getGender())) ? 1500 : 1200;
    	if (baseCalorie < minCalorie) baseCalorie = minCalorie;

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
    
    /**
    public User findById(int userId) {
        return userdao.findById(userId);
    }
    **/
}
