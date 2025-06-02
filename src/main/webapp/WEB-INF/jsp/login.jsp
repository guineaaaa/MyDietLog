<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog | 로그인</title>
    <link rel="stylesheet" href="/static/css/signup.css"> <!-- 스타일 재활용 -->
</head>
<body>
    <div class="signup-container">
        <h1 class="signup-logo">MyDietLog</h1>
        <form action="/login" method="post" class="signup-form">
            <label>아이디</label>
            <input type="text" name="loginId" required>
            <label>비밀번호</label>
            <input type="password" name="password" required>
            <button type="submit" class="signup-btn">로그인</button>
        </form>
        <c:if test="${not empty error}">
            <div style="color: red;">${error}</div>
        </c:if>
    </div>
</body>
</html>
