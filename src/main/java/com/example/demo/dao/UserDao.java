package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
	int insertUser(User user); // 생성된 PK 반환용
	User findByUserId(String userId);
}
