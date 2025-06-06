package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.ExerciseReportDto;
import com.example.demo.service.ExerciseLog.ExerciseLogService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/workout")
public class ExerciseReportController {
    @Autowired 
    private ExerciseLogService exerciseLogService;

    @GetMapping("/report")
    public String showMonthlyReport(@RequestParam(value="year", required=false) Integer year,
                                    @RequestParam(value="month", required=false) Integer month,
                                    HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) return "redirect:/login";
        // 파라미터 없으면 이번 달
        LocalDate now = LocalDate.now();
        int y = (year != null) ? year : now.getYear();
        int m = (month != null) ? month : now.getMonthValue();

        ExerciseReportDto report = exerciseLogService.getMonthlyReport(userId, y, m);
        model.addAttribute("report", report);
        return "exerciseReport"; // exerciseReport.jsp
    }
}
