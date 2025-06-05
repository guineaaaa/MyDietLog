package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DietLogDao;
import com.example.demo.model.DietLog;
import com.example.demo.model.enums.MealType;

@Service
public class DietLogServiceImpl implements DietLogService {
	@Autowired
	private DietLogDao dietLogDao;

	@Override
	public void addDietLogs(int userId, String logDate, String breakfastFood, Integer breakfastKcal, String lunchFood,
			Integer lunchKcal, String dinnerFood, Integer dinnerKcal) {
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

	@Override
	public List<DietLog> findByUserIdAndDate(int userId, String logDate) {
		return dietLogDao.findByUserIdAndDate(userId, logDate);
	}
	
	public int calculateStreak(int userId) {
		List<LocalDate> recordedDates=dietLogDao.findRecordedDates(userId);
		if(recordedDates.isEmpty())return 0;
		LocalDate today=LocalDate.now();
		int streak=0;
		
		// 오늘부터 거꾸로 연속 체크
	    for (LocalDate d = today; ; d = d.minusDays(1)) {
	        if (recordedDates.contains(d)) {
	            streak++;
	        } else {
	            break;
	        }
	    }
	    return streak;
	}
	
	public List<DietLog> findByUserIdAndDateRange(int userId, LocalDate start, LocalDate end){
		return dietLogDao.findByUserIdAndDateRange(userId,  start, end);
	}
}
