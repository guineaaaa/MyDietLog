package com.example.demo.dao.User;

import com.example.demo.model.User;

public interface UserDao {
	int insertUser(User user); // 생성된 PK 반환용
	User findByLoginId(String loginId);
	User findByLoginIdAndPassword(String loginId, String password); // 로그인 검증
	User findById(int userId);
}
