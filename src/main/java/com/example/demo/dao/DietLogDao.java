package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.DietLog;

public interface DietLogDao {
    void insert(DietLog dietLog);
    List<DietLog> findByUserIdAndDate(int userId, String logDate); // 특정 일자 전체 기록
}