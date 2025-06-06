package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.model.dto.ExerciseReportDto;
import com.example.demo.service.ExerciseLog.ExerciseLogService;
import com.example.demo.service.User.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExerciseReportController {
	@Autowired
	private ExerciseLogService exerciseLogService;
	@Autowired
	private UserService userService;

	// GET /exercise → 바로 운동 요약 페이지
	@GetMapping("/exercise")
	public String showMonthlyReport(@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "month", required = false) Integer month, HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null)
			return "redirect:/login";

		LocalDate now = LocalDate.now();
		int y = (year != null) ? year : now.getYear();
		int m = (month != null) ? month : now.getMonthValue();

		ExerciseReportDto report = exerciseLogService.getMonthlyReport(userId, y, m);
		model.addAttribute("report", report);

		// JSP에서 ${user.username} 사용 가능하게 user 정보도 넘김
		User user = userService.findById(userId);
		model.addAttribute("user", user);

		return "exerciseReport"; // /WEB-INF/jsp/exerciseReport.jsp
	}
}
