<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 | MyDietLog</title>
    <link rel="stylesheet" href="/static/css/signup.css">
</head>
<body>
    <div class="signup-container">
        <h1 class="signup-logo">MyDietLog</h1>
        <form action="/signup" method="post" class="signup-form">
            <label>아이디</label>
            <input type="text" name="loginId" required>
            
            <label>이름</label>
            <input type="text" name="username" required>
            
            <label>비밀번호</label>
            <input type="password" name="password" required>
            
            <label>키</label>
            <input type="number" name="height" required>
            
            <label>몸무게</label>
            <input type="number" name="weight" required>
            
            <div class="goal-row">
                <div class="goal-left">
                    <label>목표</label>
                    <input type="number" name="goalWeight" placeholder="어느정도 증량/감량 하고싶어요?" required>
                </div>
                
				<div class="goal-right">
                    <label class="blind">증량 및 감량</label>
                    <select name="goalType" required>
                        <option value="GAIN">증량</option>
                        <option value="LOSE">감량</option>
                    </select>
                </div>
            </div>
            <label>다짐</label>
            <input type="text" name="memo" placeholder="다짐을 적어봐요!" maxlength="100">
            
            <button type="submit" class="signup-btn">회원가입</button>
        </form>
    </div>
</body>
</html>
