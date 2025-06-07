<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar">
	<a href="/main" class="logo ${currentPath == '/main' ? 'active' : ''}">MyDietLog</a>

    <div class="nav-links">
        <a href="/daily"   class="${currentPath == '/daily'   ? 'active' : ''}">daily</a>
        <a href="/weekly"  class="${currentPath == '/weekly'  ? 'active' : ''}">weekly</a>
        <a href="/monthly" class="${currentPath == '/monthly' ? 'active' : ''}">monthly</a>
        <a href="/goal"    class="${currentPath == '/goal'    ? 'active' : ''}">goal</a>
        <a href="/exercise" class="${currentPath == '/exercise' ? 'active' : ''}">exercise</a>
    </div>
    <c:choose>
        <c:when test="${not empty sessionScope.userId}">
            <button class="nav-btn" onclick="location.href='/logout'">로그아웃</button>
        </c:when>
        <c:otherwise>
            <button class="nav-btn" onclick="location.href='/login'">로그인</button>
        </c:otherwise>
    </c:choose>
</div>
