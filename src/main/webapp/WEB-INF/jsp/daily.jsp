<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>데일리 레포트 | MyDietLog</title>
    <link rel="stylesheet" href="/static/css/daily.css">
	<link rel="stylesheet" href="/static/css/navbar.css?v=2.0">
</head>
<body>
	<%@ include file="navbar.jsp" %>
    <div class="daily-layout">
        <div class="streak-card">
            <img src="/static/img/chick.png" class="streak-img" />
            <div class="streak-number">3</div>
            <div class="streak-label">Days Streak</div>
        </div>
        <div class="report-card">
            <div class="report-label">데일리 레포트</div>
            <div class="report-desc">
                목표 달성을 위해<br>
                <b>${user.username}</b>님의 일일 권장 섭취 칼로리:<br>
                <span class="kcal-num">${recommendedCalorie}kcal</span>
            </div>
            <div class="report-today">
                ${today.monthValue}월 ${today.dayOfMonth}일<br>
                <b>${user.username}</b>님이 섭취한 칼로리:<br>
                <span class="kcal-num">${totalIntake}kcal</span><br>
                <c:if test="${totalBurned > 0}">
                    오늘 소모한 칼로리:<br>
                    <span class="kcal-num">${totalBurned}kcal</span><br>
                </c:if>
                ${user.username}님의 순섭취(섭취-운동):<br>
                <span class="kcal-num">${netCalorie}kcal</span>
            </div>
            <div class="report-msg">
                <c:choose>
                    <c:when test="${diff > 100}">
                        <span>${diff} 칼로리나 더 드셨네요!</span><br>
                        <span>너무 많이 드신거 아니에요? 운동합시다.</span>
                    </c:when>
                    <c:when test="${diff < -100}">
                        <span>${-diff} 칼로리나 덜 드셨네요!</span><br>
                        <span>너무 적게 드신 것 같아요..</span>
                    </c:when>
                    <c:otherwise>
                        <span>적절하게 드셨네요!</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
