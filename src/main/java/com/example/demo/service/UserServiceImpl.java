package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoalDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Goal;
import com.example.demo.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserDao userdao;
	@Autowired private GoalDao goalDao;
	
	@Override
    public void registerUserWithGoal(User user, Goal goal) {
        int userId = userdao.insertUser(user); // User 등록, PK 반환
        goal.setUserId(userId); // Goal 객체에 PK 세팅
        goalDao.insertGoal(goal); // Goal 등록 (User의 PK로 연결)
    }
}
