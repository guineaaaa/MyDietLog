package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ExerciseLogDao;
import com.example.demo.model.ExerciseLog;

@Service
public class ExerciseLogServiceImpl implements ExerciseLogService {
    @Autowired private ExerciseLogDao exerciseLogDao;

    @Override
    public void addExerciseLog(int userId, String logDate, String exerciseName, int exerciseTypeId, int calorieBurned) {
        exerciseLogDao.insert(new ExerciseLog(userId, logDate, exerciseTypeId, exerciseName, calorieBurned));
    }
}