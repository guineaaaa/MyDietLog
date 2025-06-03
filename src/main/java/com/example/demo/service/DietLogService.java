package com.example.demo.service;

public interface DietLogService {
    void addDietLogs(int userId, String logDate, String breakfastFood, Integer breakfastKcal,
                    String lunchFood, Integer lunchKcal, String dinnerFood, Integer dinnerKcal);
}
