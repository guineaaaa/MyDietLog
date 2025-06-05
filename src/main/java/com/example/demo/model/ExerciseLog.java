package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExerciseLog {
    private int id;
    private int userId;
    private int exerciseTypeId;
    private String exerciseName;
    private int calorieBurned;
    private Integer duration; // null 허용
    private LocalDate logDate;
    private LocalDateTime regTime;

    public ExerciseLog() {}

    public ExerciseLog(int userId, String logDate, int exerciseTypeId, String exerciseName, int duration,int calorieBurned) {
        this.userId = userId;
        this.logDate = LocalDate.parse(logDate);
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseName = exerciseName;
        this.duration=duration;
        this.calorieBurned = calorieBurned;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getExerciseTypeId() { return exerciseTypeId; }
    public void setExerciseTypeId(int exerciseTypeId) { this.exerciseTypeId = exerciseTypeId; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public int getCalorieBurned() { return calorieBurned; }
    public void setCalorieBurned(int calorieBurned) { this.calorieBurned = calorieBurned; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }

    public LocalDateTime getRegTime() { return regTime; }
    public void setRegTime(LocalDateTime regTime) { this.regTime = regTime; }
}
