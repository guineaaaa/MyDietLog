package com.example.demo.dao.ExerciseLog;

import java.util.List;

import com.example.demo.model.ExerciseLog;
public interface ExerciseLogDao {
    void insert(ExerciseLog log);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
}