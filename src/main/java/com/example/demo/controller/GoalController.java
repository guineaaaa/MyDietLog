package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Goal;
import com.example.demo.model.enums.GoalType;
import com.example.demo.service.GoalService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * API 흐름 및 설계
 * GET /goal/edit: 내 목표 정보를 폼에 미리 보여줌
 * POST /goal/edit: 수정된 목표 정보를 저장
 */
@Controller
public class GoalController {
	@Autowired
	private GoalService goalService;
	@Autowired
	private UserService userService;
	
	// 목표 수정 폼 - 현재 목표 보여주기
    // 1. 목표 수정 폼 - 현재 목표를 보여줌
    @GetMapping("/goal")
    public String showGoalEditForm(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        Goal goal = goalService.findLatestGoalByUserId(userId);
        model.addAttribute("goal", goal);
        model.addAttribute("goalTypes", GoalType.values());
        return "goal"; 
    }
    
    // 2. 목표 수정 처리 
    @PostMapping("/goal/edit")
    public String editGoal(@ModelAttribute Goal formGoal, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        formGoal.setUserId(userId);
        goalService.updateGoal(formGoal);
     
        return "redirect:/main";
    }

}
