package com.example.demo.dao.DietLog;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.DietLog;

public interface DietLogDao {
    void insert(DietLog dietLog);
    List<DietLog> findByUserIdAndDate(int userId, String logDate); // 특정 일자 전체 기록
    List<LocalDate> findRecordedDates(int userId);
    List<DietLog> findByUserIdAndDateRange(int userId, LocalDate startDate, LocalDate endDate);
}