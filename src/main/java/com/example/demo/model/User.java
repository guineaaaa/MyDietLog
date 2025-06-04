package com.example.demo.model;

import java.time.LocalDateTime;
import com.example.demo.model.enums.Gender; 

public class User {
	private int id; // PK
	private String username;// 로그인용
	private String loginId;
	private String password;
	private Gender gender;
	private Integer height;
	private Integer weight;
	private LocalDateTime regDate;
	private Integer recommendedCalorie;

	// 기본 생성자
	public User() {
	}

	// 모든 필드에 대한 getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}
	
    public Gender getGender() { return gender; }
    public void setGender(String gender) {
        if (gender != null) {
            this.gender = Gender.valueOf(gender.toUpperCase());
        } else {
            this.gender = null;
        }
    }
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
    public Integer getRecommendedCalorie() {
        return recommendedCalorie;
    }
    public void setRecommendedCalorie(Integer recommendedCalorie) {
        this.recommendedCalorie = recommendedCalorie;
    }

}
