package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.example.demo.model.enums.MealType; 

public class DietLog {
    private int id;
    private int userId;
    private LocalDate logDate;
    private MealType mealType;  // "BREAKFAST", "LUNCH", "DINNER"
    private String foodName;
    private int calorie;
    private LocalDateTime regTime;

    public DietLog() {}

    // 편의상 오버로딩 생성자(필요시)
    public DietLog(int userId, String logDate,  MealType mealType,  String foodName, int calorie) {
        this.userId = userId;
        this.logDate = LocalDate.parse(logDate);
        this.mealType = mealType;
        this.foodName = foodName;
        this.calorie = calorie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }

    public MealType getMealType() { return mealType; }
    public void setMealType(MealType mealType) { this.mealType = mealType; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public int getCalorie() { return calorie; }
    public void setCalorie(int calorie) { this.calorie = calorie; }

    public LocalDateTime getRegTime() { return regTime; }
    public void setRegTime(LocalDateTime regTime) { this.regTime = regTime; }
}
