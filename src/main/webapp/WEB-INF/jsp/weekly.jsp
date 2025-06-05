<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>주간 레포트 | MyDietLog</title>
    <link rel="stylesheet" href="/static/css/weekly.css">
	<link rel="stylesheet" href="/static/css/navbar.css?v=2.0">
</head>
<body>
	<%@ include file="navbar.jsp" %>
    <div class="weekly-layout">
        <div class="streak-card">
            <img src="/static/img/chick.png" class="streak-img" />
            <div class="streak-number">이번주 중</div>
            <div class="streak-label">${recordedDays}일 기록했어요</div>
        </div>
        <div class="report-card">
            <div class="report-label">주간 레포트</div>
            <div class="report-desc">
                <b>${user.username}</b>님이 일주일 동안<br><br>
                총 섭취한 칼로리: <b>${totalCalorie}kcal</b><br>
                이번주 하루 평균 섭취 칼로리: <b>${avgCalorie}kcal</b>
            </div>
        </div>
    </div>
</body>
</html>
