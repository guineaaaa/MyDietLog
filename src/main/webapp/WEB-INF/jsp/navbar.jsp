<!-- src/main/webapp/WEB-INF/jsp/navbar.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="navbar">
    <span class="logo" style="cursor:pointer;" onclick="location.href='/main'">MyDietLog</span>
    <div class="nav-links">
        <a href="/daily">daily</a>
        <a href="/weekly">weekly</a>
        <a href="/monthly">monthly</a>
        <a href="/goal">goal</a>
		<a href="/exercise">exercise</a>
    </div>
    <button class="nav-btn" onclick="location.href='/login'">로그인</button>
</div>
