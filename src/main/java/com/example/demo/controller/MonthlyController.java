package com.example.demo.controller;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MonthlyController {
	@Autowired
	private UserService userService;
	@Autowired
	private DietLogService dietLogService;
	
	@GetMapping("/monthly")
	public String monthlyReport(Model model, HttpSession session, HttpServletRequest request) {
		Integer userId=(Integer) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/login";
		}
		
		User user=userService.findById(userId);
		
		// 이번달의 시작과 끝 날짜 구하기
		LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);
        LocalDate monthStart = currentMonth.atDay(1);
        LocalDate monthEnd = currentMonth.atEndOfMonth();
        
        // 이번달 식단 기록 가져오기
        List<DietLog> monthLogs=dietLogService.findByUserIdAndDateRange(userId,monthStart, monthEnd);
        
        // 날짜 별 칼로리 합산
        Map<LocalDate, Integer> dayCalorieSum=new HashMap<>();
        for (DietLog log : monthLogs) {
            LocalDate date = log.getLogDate();
            int sum = dayCalorieSum.getOrDefault(date, 0);
            sum += log.getCalorie();
            dayCalorieSum.put(date, sum);
        }
        
        int recordedDays=dayCalorieSum.size();
        int totalCalorie = dayCalorieSum.values().stream().mapToInt(Integer::intValue).sum();
        int avgCalorie = (recordedDays > 0) ? totalCalorie / recordedDays : 0;
        
        // 모델에 담기
        model.addAttribute("user", user);
        model.addAttribute("month", currentMonth.getMonthValue());
        model.addAttribute("recordedDays", recordedDays);
        model.addAttribute("totalCalorie", totalCalorie);
        model.addAttribute("avgCalorie", avgCalorie);
        
     // 현재 경로 넘기기 (navbar)
        model.addAttribute("currentPath", request.getServletPath());


        return "monthly"; 
	}

}
