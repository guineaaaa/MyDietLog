package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.ExerciseType;
public interface ExerciseTypeDao {
    List<ExerciseType> findAll();
}