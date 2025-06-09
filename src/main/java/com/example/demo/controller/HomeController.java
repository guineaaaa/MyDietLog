package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DietLog;
import com.example.demo.model.ExerciseLog;
import com.example.demo.model.ExerciseType;
import com.example.demo.model.Goal;
import com.example.demo.model.User;
import com.example.demo.service.DietLog.DietLogService;
import com.example.demo.service.ExerciseLog.ExerciseLogService;
import com.example.demo.service.ExerciseType.ExerciseTypeService;
import com.example.demo.service.Goal.GoalService;
import com.example.demo.service.User.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserService userService;
	@Autowired
	private GoalService goalService;
	@Autowired
	private DietLogService dietLogService;
	@Autowired
	private ExerciseLogService exerciseLogService;
	@Autowired
	private ExerciseTypeService exerciseTypeService;

	// 랜딩페이지
	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}

	@GetMapping("/main")
	public String main(@RequestParam(value="date", required=false)String date, Model model, HttpSession session, HttpServletRequest request) {
		 System.out.println(">>> /main date 파라미터: " + date); 
		Integer userId=(Integer) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/login";
		}
			String selectedDate = (date != null) ? date : LocalDate.now().toString();
	        
			// 사용자의 목표 객체 가져오기
			User user = userService.findById(userId);
	        Goal goal = goalService.findLatestGoalByUserId(userId);
	        
	        List<DietLog> dietLogs = dietLogService.findByUserIdAndDate(userId, selectedDate);
	        List<ExerciseLog> exerciseLogs = exerciseLogService.findByUserIdAndDate(userId, selectedDate);
	        
	        // 메인 페이지에 사용자가 선택 가능한 운동 리스트 가져오기
	        List<ExerciseType> exerciseTypes=exerciseTypeService.findAll();
	        model.addAttribute("exerciseTypes", exerciseTypes);
	        
	        int progress = goalService.calcGoalProgressPercent(goal);

	        int totalIntake = dietLogs.stream().mapToInt(DietLog::getCalorie).sum();
	        int totalBurned = exerciseLogs.stream().mapToInt(ExerciseLog::getCalorieBurned).sum();
	        int netCalorie = totalIntake - totalBurned;
	        int targetCalorie = (user.getRecommendedCalorie() != null) ? user.getRecommendedCalorie() : 0;
	        int diff = netCalorie - targetCalorie;
	        String calorieState = (diff > 0) ? "over" : (diff < -200 ? "under" : "good");

	        model.addAttribute("user", user);
	        model.addAttribute("goal", goal);
	        model.addAttribute("progress", progress);
	        model.addAttribute("dietLogs", dietLogs);
	        model.addAttribute("exerciseLogs", exerciseLogs);
	        model.addAttribute("calorieState", calorieState);
	        model.addAttribute("selectedDate", selectedDate);
	        model.addAttribute("netCalorie", netCalorie);
	        model.addAttribute("targetCalorie", targetCalorie);
	        
	     // 현재 경로 넘기기 (navbar)
	        model.addAttribute("currentPath", request.getServletPath());

	        return "main"; // main.jsp로 이동
	    
	}
		
	    // 식단 기록 저장
	    @PostMapping("/dietlog/add")
	    public String addDietLog(@RequestParam String date,
	                             @RequestParam String breakfastFood, @RequestParam Integer breakfastKcal,
	                             @RequestParam String lunchFood, @RequestParam Integer lunchKcal,
	                             @RequestParam String dinnerFood, @RequestParam Integer dinnerKcal,
	                             HttpSession session) {
	        Integer userId = (Integer) session.getAttribute("userId");
	        if (userId == null) return "redirect:/login";
	        dietLogService.addDietLogs(userId, date, breakfastFood, breakfastKcal, lunchFood, lunchKcal, dinnerFood, dinnerKcal);
	        return "redirect:/main";
	    }
	    
	    // 운동 기록 저장
	    @PostMapping("/exerciselog/add")
	    public String addExerciseLog(@RequestParam String date,
	                                 @RequestParam String exerciseName,
	                                 @RequestParam Integer exerciseTypeId,
	                                 @RequestParam Integer calorieBurned,
	                                 @RequestParam Integer duration,
	                                 HttpSession session) {
	        Integer userId = (Integer) session.getAttribute("userId");
	        if (userId == null) return "redirect:/login";
	        exerciseLogService.addExerciseLog(userId, date, exerciseName, duration,exerciseTypeId, calorieBurned);
	        return "redirect:/main";
	    }

	
}