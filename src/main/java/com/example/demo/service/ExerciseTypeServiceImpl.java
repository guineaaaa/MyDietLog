package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.ExerciseType;
import com.example.demo.dao.ExerciseTypeDao;

@Service
public class ExerciseTypeServiceImpl implements ExerciseTypeService {
    @Autowired private ExerciseTypeDao exerciseTypeDao;

    @Override
    public List<ExerciseType> findAll() {
        return exerciseTypeDao.findAll();
    }
}