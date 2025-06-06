<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>운동 레포트</title>
    <link rel="stylesheet" type="text/css" href="/static/css/exerciseReport.css">
	<link rel="stylesheet" href="/static/css/navbar.css?v=2.0">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="exercise-report-layout">
    <div class="exercise-type-stats">
        <div class="report-label">운동 레포트</div>
        <c:forEach var="entry" items="${report.typeCounts}">
            <div>${entry.key}: ${entry.value}회</div>
        </c:forEach>
    </div>
    <div class="exercise-summary-card">
        <div class="summary-title">${report.month}월 중</div>
        <div class="summary-main">
            <b>${user.username}</b> 님이 제일 많이 한 운동은? : <b>${report.mostType}</b>
        </div>
        <br>
        <div>운동에 투자한 시간 총합: <b>${report.totalDuration}</b>분</div>
        <div>운동에 소모한 칼로리 총합: <b>${report.totalKcal}</b>kcal</div>
        <br>
        <div>하루 평균 운동 시간: <b>${report.avgDuration}</b>분</div>
        <div>하루 평균 소모 칼로리: <b>${report.avgKcal}</b>kcal</div>
    </div>
</div>
</body>
</html>
