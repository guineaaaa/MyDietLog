<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog</title>
    <link rel="stylesheet" href="/static/css/mainpage.css?v=2.0">
    <link rel="stylesheet" href="/static/css/navbar.css?v=2.0">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="main-container">
        <div class="left-box">
            <hr>
            <h1>
                나만의 식단 기록 &<br>건강 관리 웹서비스
            </h1>
            <button class="main-btn"
			onclick="location.href='/signup'">회원가입</button>
        </div>
        <div class="right-img">
            <img src="/static/img/chick.png" alt="병아리 로고">
        </div>
    </div>
</body>
</html>
