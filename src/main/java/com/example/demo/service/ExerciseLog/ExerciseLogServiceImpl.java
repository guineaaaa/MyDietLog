package com.example.demo.service.ExerciseLog;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExerciseLog.ExerciseLogDao;
import com.example.demo.dao.ExerciseType.ExerciseTypeDao;
import com.example.demo.model.ExerciseLog;
import com.example.demo.model.ExerciseType;
import com.example.demo.model.dto.ExerciseReportDto;

@Service
public class ExerciseLogServiceImpl implements ExerciseLogService {
	
	@Autowired private ExerciseLogDao exerciseLogDao;
    @Autowired private ExerciseTypeDao exerciseTypeDao;
    
    @Override
    public void addExerciseLog(int userId, String logDate, String exerciseName, int duration, int exerciseTypeId, int calorieBurned) {
        exerciseLogDao.insert(new ExerciseLog(userId, logDate, exerciseTypeId, exerciseName, duration, calorieBurned));
    }
    
    @Override
    public List<ExerciseLog> findByUserIdAndDate(int userId, String logDate) {
        return exerciseLogDao.findByUserIdAndDate(userId, logDate);
    }

    
    public ExerciseReportDto getMonthlyReport(int userId, int year, int month) {
        // 1. 기간 구하기
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        // 2. 이달의 운동기록 전체 가져오기
        List<ExerciseLog> logs = exerciseLogDao.findByUserIdAndDateRange(userId, start, end);
        // 3. 운동 분류 가져오기
        List<ExerciseType> types = exerciseTypeDao.findAll();

        // 4. 운동별 횟수 세기
        Map<Integer, Integer> typeCountMap = new HashMap<>();
        for (ExerciseLog log : logs) {
            typeCountMap.put(log.getExerciseTypeId(),
                typeCountMap.getOrDefault(log.getExerciseTypeId(), 0) + 1);
        }

        // 5. 가장 많이 한 운동 구하기
        int maxTypeId = -1;
        int maxCnt = 0;
        for (var entry : typeCountMap.entrySet()) {
            if (entry.getValue() > maxCnt) {
                maxCnt = entry.getValue();
                maxTypeId = entry.getKey();
            }
        }
        
        String mostTypeName = "없음";
        for (ExerciseType t : types) {
            if (t.getId() == maxTypeId) {
                mostTypeName = t.getTypeName();
                break;
            }
        }

        // 6. 총합, 평균
        int totalDuration = logs.stream().mapToInt(log -> log.getDuration() != null ? log.getDuration() : 0).sum();
        int totalKcal = logs.stream().mapToInt(ExerciseLog::getCalorieBurned).sum();
        long activeDays = logs.stream().map(ExerciseLog::getLogDate).distinct().count();
        int avgDuration = activeDays > 0 ? (totalDuration / (int)activeDays) : 0;
        int avgKcal = activeDays > 0 ? (totalKcal / (int)activeDays) : 0;

        // 7. 운동별 이름->횟수
        Map<String, Integer> countPerType = new LinkedHashMap<>();
        for (ExerciseType t : types) {
            int count = (int) logs.stream().filter(log -> log.getExerciseTypeId() == t.getId()).count();
            countPerType.put(t.getTypeName(), count);
        }

        // 8. DTO 세팅
        ExerciseReportDto dto = new ExerciseReportDto();
        dto.setMonth(month);
        dto.setTypeCounts(countPerType);
        dto.setMostType(mostTypeName);
        dto.setTotalDuration(totalDuration);
        dto.setTotalKcal(totalKcal);
        dto.setAvgDuration(avgDuration);
        dto.setAvgKcal(avgKcal);
        return dto;
    }
    
    
}