package com.example.demo.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.DietLog;
import com.example.demo.model.ExerciseLog;
import com.example.demo.model.User;
import com.example.demo.service.DietLogService;
import com.example.demo.service.ExerciseLogService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DailyController {
	@Autowired
	private UserService userService;
	@Autowired
	private DietLogService dietLogService;
	@Autowired
	private ExerciseLogService exerciseLogService;
	
	@GetMapping("/daily")
	public String dailyReport(Model model, HttpSession session) {
		Integer userId=(Integer)session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/login";
		}
        User user = userService.findById(userId);
        LocalDate today = LocalDate.now();
        int streak=dietLogService.calculateStreak(userId);
        
        // 1. 오늘의 식단 기록 가져오기 (아침, 점심, 저녁)
        List<DietLog> dietLogs = dietLogService.findByUserIdAndDate(userId, today.toString());
        int totalIntake = dietLogs.stream().mapToInt(DietLog::getCalorie).sum();
        
        // 2. 오늘의 운동 기록 가져오기
        List<ExerciseLog> exerciseLogs = exerciseLogService.findByUserIdAndDate(userId, today.toString());
        int totalBurned = exerciseLogs.stream().mapToInt(ExerciseLog::getCalorieBurned).sum();
        
        // 3. User의 권장 칼로리와 비교
        int recommendedCalorie = (user.getRecommendedCalorie() != null) ? user.getRecommendedCalorie() : 0;
        int netCalorie = totalIntake - totalBurned;
        int diff = netCalorie - recommendedCalorie;
        
        // 4. 메세지 결정
        String calorieMsg;
        if (diff >= -100 && diff <= 100) {
            calorieMsg = "오늘 적절하게 드셨네요!";
        } else if (diff > 100) {
            calorieMsg = "너무 많이 드신거 아니예요? 운동합시다.";
        } else { // diff < -100
            calorieMsg = "너무 적게 드신것같아요..";
        }
        
        // 5. 모델에 전달
        model.addAttribute("user", user);
        model.addAttribute("recommendedCalorie", recommendedCalorie);
        model.addAttribute("today", today);
        model.addAttribute("totalIntake", totalIntake);
        model.addAttribute("totalBurned", totalBurned);
        model.addAttribute("netCalorie", netCalorie);
        model.addAttribute("diff", diff);
        model.addAttribute("calorieMsg", calorieMsg);
        model.addAttribute("streak", streak);

        return "daily";
	}

}
