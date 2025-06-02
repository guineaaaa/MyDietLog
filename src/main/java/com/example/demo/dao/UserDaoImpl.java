package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
    // DataSource 주입, JDBC Template 사용 등 생략
    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO User (username, loginId, password, height, weight) VALUES (?, ?, ?, ?, ?)";
        // KeyHolder 사용해서 user.id 자동생성 반환
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getLoginId());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getHeight());
            ps.setInt(5, user.getWeight());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User findByLoginId(String loginId) {
        String sql = "SELECT * FROM User WHERE loginId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{loginId}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setLoginId(rs.getString("loginId"));
            user.setPassword(rs.getString("password"));
            user.setHeight(rs.getInt("height"));
            user.setWeight(rs.getInt("weight"));
            user.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
            return user;
        });
    }
	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
	    String sql = "SELECT * FROM User WHERE loginId = ? AND password = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{loginId, password}, (rs, rowNum) -> {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setUsername(rs.getString("username"));
	            user.setLoginId(rs.getString("loginId"));
	            user.setPassword(rs.getString("password"));
	            user.setHeight(rs.getInt("height"));
	            user.setWeight(rs.getInt("weight"));
	            user.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
	            return user;
	        });
	    } catch (Exception e) {
	        return null; // 로그인 실패 시 null 반환
	    }
	}

}
