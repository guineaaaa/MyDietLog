package com.example.demo.service;

public interface ExerciseLogService {
    void addExerciseLog(int userId, String logDate, String exerciseName, int exerciseTypeId, int calorieBurned);
}