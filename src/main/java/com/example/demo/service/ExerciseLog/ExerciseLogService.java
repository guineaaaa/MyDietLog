package com.example.demo.service.ExerciseLog;

import java.util.List;

import com.example.demo.model.ExerciseLog;

public interface ExerciseLogService {
    void addExerciseLog(int userId, String logDate, String exerciseName, int duration, int exerciseTypeId, int calorieBurned);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
}