package com.example.demo.dao.User;

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
	// Spring이 자동 주입하는 JdbcTemplate (DB 연결 및 쿼리 실행 도구)
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
    /**
     * 새로운 사용자를 User 테이블에 삽입(insert)하고, 생성된 PK(id)를 반환한다.
     * 
     * @param user  저장할 User 객체
     * @return      생성된 사용자의 PK(id)
     */
	@Override
	public int insertUser(User user) {
	    String sql = "INSERT INTO User (username, loginId, password, gender, height, weight, recommended_calorie) VALUES (?, ?, ?, ?, ?, ?,?)";
	    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	    jdbcTemplate.update(con -> {
	        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getLoginId());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getGender().name()); // 추가
	        ps.setInt(5, user.getHeight());
	        ps.setInt(6, user.getWeight());
	        ps.setInt(7, user.getRecommendedCalorie());
	        return ps;
	    }, keyHolder);
	    // 생성된 PK(id)를 반환
	    return keyHolder.getKey().intValue();
	}

    /**
     * loginId로 사용자 정보를 조회한다.
     * 
     * @param loginId   조회할 사용자의 로그인 ID
     * @return          해당 loginId를 가진 User 객체 (없으면 예외 발생)
     */
    @Override
    public User findByLoginId(String loginId) {
        String sql = "SELECT * FROM User WHERE loginId = ?";
        // 쿼리 실행 후, 결과 ResultSet을 User 객체로 매핑
        return jdbcTemplate.queryForObject(sql, new Object[]{loginId}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setLoginId(rs.getString("loginId"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setHeight(rs.getInt("height"));
            user.setWeight(rs.getInt("weight"));
            user.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
            return user;
        });
    }
    
    /**
     * loginId와 password가 일치하는 사용자를 조회한다.
     * 
     * @param loginId   로그인 ID
     * @param password  비밀번호
     * @return          로그인 성공시 User 객체, 실패시 null 반환
     */
	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
	    String sql = "SELECT * FROM User WHERE loginId = ? AND password = ?";
	    try {
	    	// 아이디와 비밀번호가 일치하는 사용자 반환
	        return jdbcTemplate.queryForObject(sql, new Object[]{loginId, password}, (rs, rowNum) -> {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setUsername(rs.getString("username"));
	            user.setLoginId(rs.getString("loginId"));
	            user.setPassword(rs.getString("password"));
	            user.setGender(rs.getString("gender"));
	            user.setHeight(rs.getInt("height"));
	            user.setWeight(rs.getInt("weight"));
	            user.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
	            return user;
	        });
	    } catch (Exception e) {
	        return null; // 로그인 실패 시 null 반환
	    }
	}
	
    /**
     * PK(id)로 사용자 정보를 조회한다.
     * 
     * @param userId    사용자 PK(id)
     * @return          User 객체 (없으면 예외 발생)
     */
	@Override
	public User findById(int userId) {
	    String sql = "SELECT * FROM User WHERE id = ?";
	    return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
	        User user = new User();
	        user.setId(rs.getInt("id"));
	        user.setUsername(rs.getString("username"));
	        user.setLoginId(rs.getString("loginId"));
	        user.setPassword(rs.getString("password"));
	        user.setGender(rs.getString("gender")); // String → Enum
	        user.setHeight(rs.getInt("height"));
	        user.setWeight(rs.getInt("weight"));
	        user.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
	        user.setRecommendedCalorie(rs.getInt("recommended_calorie"));
	        return user;
	    });
	}

}
