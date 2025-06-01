<!-- src/main/webapp/WEB-INF/jsp/index.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog</title>
    <link rel="stylesheet" href="/static/css/main.css?v=1.0">
    <link rel="stylesheet" href="/static/css/navbar.css?v=1.0"><!-- 네비바 스타일 별도 import -->
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="main-container">
        <div class="left-box">
            <hr>
            <h1>
                나만의 식단 기록 &<br>건강 관리 웹서비스
            </h1>
            <div class="desc"></div>
            <button class="main-btn">회원가입</button>
        </div>
        <div class="right-img">
            <img src="/static/img/chick.png" alt="병아리 로고">
        </div>
    </div>
</body>
</html>
