package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Goal;

public interface UserService {
	void registerUserWithGoal(User user, Goal goal);
}
