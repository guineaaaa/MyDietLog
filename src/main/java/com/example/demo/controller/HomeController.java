package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
@Controller
public class HomeController {
	
	@Autowired
    private DataSource dataSource;
	
	// 랜딩페이지
	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}
	
    @GetMapping("/test-db")
    @ResponseBody
    public String testDb() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return "DB 연결 성공!";
            } else {
                return "DB 연결 실패!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "DB 연결 중 에러: " + e.getMessage();
        }
    }
}