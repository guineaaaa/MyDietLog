package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.ExerciseLog;
public interface ExerciseLogDao {
    void insert(ExerciseLog log);
    List<ExerciseLog> findByUserIdAndDate(int userId, String logDate);
}