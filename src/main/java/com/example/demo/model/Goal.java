package com.example.demo.model;

import java.time.LocalDate;
import com.example.demo.model.enums.GoalType;

public class Goal {
	private int id; //PK
	
	// int는 null을 가질 수 없지만, Integer는 null 가능
	private Integer userId; // FK(User의 id, int)
	private double goalWeight;
	private GoalType goalType; // "GAIN" or "LOSE"
	private String memo;
	private LocalDate startDate;
	private LocalDate endDate;

	// 기본 생성자
	public Goal() {
	}

	// Getter, Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getGoalWeight() {
		return goalWeight;
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
