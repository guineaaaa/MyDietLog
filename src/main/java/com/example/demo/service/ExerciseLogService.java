package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ExerciseLog;

public interface ExerciseLogService {
    void addExerciseLog(int userId, String logDate, String exerciseName, int exerciseTypeId, int calorieBurned);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
}