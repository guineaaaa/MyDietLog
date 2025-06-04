package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DietLogDao;
import com.example.demo.model.DietLog;
import com.example.demo.model.enums.MealType;
@Service
public class DietLogServiceImpl implements DietLogService {
    @Autowired private DietLogDao dietLogDao;
    @Override
    public void addDietLogs(int userId, String logDate, String breakfastFood, Integer breakfastKcal,
                            String lunchFood, Integer lunchKcal, String dinnerFood, Integer dinnerKcal) {
        // 아침
        if (breakfastFood != null && !breakfastFood.isEmpty() && breakfastKcal != null) {
            dietLogDao.insert(new DietLog(userId, logDate, MealType.BREAKFAST, breakfastFood, breakfastKcal));
        }
        // 점심
        if (lunchFood != null && !lunchFood.isEmpty() && lunchKcal != null) {
            dietLogDao.insert(new DietLog(userId, logDate, MealType.LUNCH, lunchFood, lunchKcal));
        }
        // 저녁
        if (dinnerFood != null && !dinnerFood.isEmpty() && dinnerKcal != null) {
            dietLogDao.insert(new DietLog(userId, logDate, MealType.DINNER, dinnerFood, dinnerKcal));
        }
    }
}
