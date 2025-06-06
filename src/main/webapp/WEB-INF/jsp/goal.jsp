<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Goal 수정 | MyDietLog</title>
    <link rel="stylesheet" href="/static/css/goal.css">
    <link rel="stylesheet" href="/static/css/navbar.css?v=2.0">
  
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="goal-edit-container">
    <h2>Goal 수정</h2>
    <form action="/goal/edit" method="post" class="goal-edit-form">
        <input type="hidden" name="id" value="${goal.id}"/>
        <div class="goal-row">
            <input type="date" name="startDate" value="${goal.startDate}" required class="goal-input"/>
            <input type="date" name="endDate" value="${goal.endDate}" required class="goal-input"/>
        </div>
        <div class="goal-row">
            <input type="number" name="goalWeight" value="${goal.goalWeight}" placeholder="목표 체중(kg)" required class="goal-input"/>
            <select name="goalType" required class="goal-input">
                <c:forEach var="type" items="${goalTypes}">
                    <option value="${type}" <c:if test="${goal.goalType == type}">selected</c:if>>${type eq 'GAIN' ? '증량' : '감량'}</option>
                </c:forEach>
            </select>
        </div>
        <label>다짐</label>
        <input type="text" name="memo" value="${goal.memo}" placeholder="다짐을 적어봐요!" maxlength="100"/>
        <button type="submit" class="goal-edit-btn">수정</button>
    </form>
</div>

</body>
</html>
