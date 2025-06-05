package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.DietLog;

public interface DietLogService {
    void addDietLogs(int userId, String logDate, String breakfastFood, Integer breakfastKcal,
                     String lunchFood, Integer lunchKcal, String dinnerFood, Integer dinnerKcal);
    List<DietLog> findByUserIdAndDate(int userId, String logDate);
    int calculateStreak(int userId);
    List<DietLog> findByUserIdAndDateRange(int userId, LocalDate startDate, LocalDate endDate);
}