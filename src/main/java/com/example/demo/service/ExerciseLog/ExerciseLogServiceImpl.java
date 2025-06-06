package com.example.demo.service.ExerciseLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExerciseLog.ExerciseLogDao;
import com.example.demo.model.ExerciseLog;

@Service
public class ExerciseLogServiceImpl implements ExerciseLogService {
    @Autowired private ExerciseLogDao exerciseLogDao;

    @Override
    public void addExerciseLog(int userId, String logDate, String exerciseName, int duration, int exerciseTypeId, int calorieBurned) {
        exerciseLogDao.insert(new ExerciseLog(userId, logDate, exerciseTypeId, exerciseName, duration, calorieBurned));
    }
    
    @Override
    public List<ExerciseLog> findByUserIdAndDate(int userId, String logDate) {
        return exerciseLogDao.findByUserIdAndDate(userId, logDate);
    }
}