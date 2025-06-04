<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog - 메인</title>
    <link rel="stylesheet" href="/static/css/navbar.css">
    <link rel="stylesheet" href="/static/css/main.css">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="dashboard-container">
    <div class="dash-left">
        <div class="user-summary">
            <div><b>${user.username}</b></div>
            <div>목표: ${goal.endDate}까지 ${goal.goalWeight}kg ${goal.goalType == 'LOSE' ? '감량' : '증량'}</div>
            <div>다짐: ${goal.memo}</div>
        </div>
        <div class="calendar-area">
            <!-- 캘린더 라이브러리 자리 -->
            <div id="calendar"></div>
        </div>
    </div>
    <div class="dash-right">
        <div class="progress-bar-area">
            <div class="progress-label">${progress}%</div>
            <div class="progress-bar-bg">
                <div class="progress-bar-fill" style="width: ${progress}%"></div>
            </div>
        </div>
        <div class="diet-records">
            <div class="record-label">식단기록</div>
            <div>아침: <c:forEach var="d" items="${dietLogs}">${d.mealType == 'BREAKFAST' ? d.foodName+'('+d.calorie+')' : ''}</c:forEach></div>
            <div>점심: <c:forEach var="d" items="${dietLogs}">${d.mealType == 'LUNCH' ? d.foodName+'('+d.calorie+')' : ''}</c:forEach></div>
            <div>저녁: <c:forEach var="d" items="${dietLogs}">${d.mealType == 'DINNER' ? d.foodName+'('+d.calorie+')' : ''}</c:forEach></div>
        </div>
        <div class="exercise-records">
            <div class="record-label">운동기록</div>
            <div>운동명 | 종류 | 소모칼로리</div>
            <c:forEach var="e" items="${exerciseLogs}">
                <div>${e.exerciseName} | ${e.exerciseTypeId} | ${e.calorieBurned}</div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/static/js/calendar.js"></script>
</body>
</html>
