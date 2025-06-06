package com.example.demo.service.User;

import com.example.demo.model.User;
import com.example.demo.model.Goal;

public interface UserService {
	void registerUserWithGoal(User user, Goal goal);
	User login(String loginId, String password);
	User findById(int userId);
}
