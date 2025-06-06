package com.example.demo.service.ExerciseLog;

import java.util.List;

import com.example.demo.model.ExerciseLog;
import com.example.demo.model.dto.ExerciseReportDto;

public interface ExerciseLogService {
    void addExerciseLog(int userId, String logDate, String exerciseName, int duration, int exerciseTypeId, int calorieBurned);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
    ExerciseReportDto getMonthlyReport(int userId, int year, int month);
}