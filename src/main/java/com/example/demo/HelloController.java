package com.example.demo;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}
}