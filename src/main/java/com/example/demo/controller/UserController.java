package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.Goal;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

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

	// 회원가입+목표 등록 처리
	@PostMapping("/signup")
	public String signupSubmit(@ModelAttribute User user, @ModelAttribute Goal goal) {
		userService.registerUserWithGoal(user, goal);
		return "redirect:/"; // 회원가입 후 랜딩페이지로 이동
	}

	// 로그인 폼
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String loginSubmit(@RequestParam String loginId, @RequestParam String password, Model model,
			HttpSession session) {
		User user = userService.login(loginId, password);
		if (user != null) {
			session.setAttribute("userId", user.getId()); // userId만 저장
			return "redirect:/main"; // 메인 페이지로 이동
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "login"; // 로그인 화면으로 다시 이동
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}

}
