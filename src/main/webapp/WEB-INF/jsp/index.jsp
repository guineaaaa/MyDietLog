<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head> <meta charset="UTF-8"> </head>
<body>
<h1> Index Page </h1>
<%=new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()) %>
</body>
</html> 