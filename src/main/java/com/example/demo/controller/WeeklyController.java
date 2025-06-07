package com.example.demo.controller;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.DietLog;
import com.example.demo.model.User;
import com.example.demo.service.DietLog.DietLogService;
import com.example.demo.service.User.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class WeeklyController {
    @Autowired private UserService userService;
    @Autowired private DietLogService dietLogService;

    @GetMapping("/weekly")
    public String weeklyReport(Model model, HttpSession session, HttpServletRequest request){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        User user = userService.findById(userId);

        // 이번주 시작/끝 날짜 구하기 (월~일 기준, 오늘 포함)
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - 1); // 월요일
        LocalDate weekEnd = weekStart.plusDays(6); // 일요일

        // 이번주 식단 기록 모두 가져오기
        List<DietLog> weekLogs = dietLogService.findByUserIdAndDateRange(userId, weekStart, weekEnd);

        // 날짜별 합계 계산
        Map<LocalDate, Integer> dayCalorieSum = new HashMap<>();
        for (DietLog log : weekLogs) {
            LocalDate date = log.getLogDate();
            int sum = dayCalorieSum.getOrDefault(date, 0);
            sum += log.getCalorie();
            dayCalorieSum.put(date, sum);
        }

        int recordedDays = dayCalorieSum.size();
        int totalCalorie = dayCalorieSum.values().stream().mapToInt(Integer::intValue).sum();
        int avgCalorie = (recordedDays > 0) ? totalCalorie / recordedDays : 0;

        // 모델에 담기
        model.addAttribute("user", user);
        model.addAttribute("recordedDays", recordedDays);
        model.addAttribute("totalCalorie", totalCalorie);
        model.addAttribute("avgCalorie", avgCalorie);
        
     // 현재 경로 넘기기 (navbar)
        model.addAttribute("currentPath", request.getServletPath());


        return "weekly"; // weekly.jsp
    }
}
