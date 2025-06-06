package com.example.demo.dao.ExerciseLog;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.ExerciseLog;
public interface ExerciseLogDao {
    void insert(ExerciseLog log);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
    List<ExerciseLog> findByUserIdAndDateRange(int userId, LocalDate start, LocalDate end);
}