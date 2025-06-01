package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.model.Goal;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	// 회원가입 폼
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("goal", new Goal());
		return "signup";
	}
	
	// 회원가입 처리
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, @ModelAttribute Goal goal, Model model) {
        userService.registerUserWithGoal(user, goal);
        return "signup_success"; // 성공 안내
    }
}
