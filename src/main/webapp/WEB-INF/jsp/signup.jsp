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
            
            <label>성별</label>
            <div class="gender-row">
                <label class="gender-label">
                    <input type="radio" name="gender" value="FEMALE" required checked>
                    <span class="custom-radio"></span>
                    <span class="gender-text">여성</span>
                </label>
                <label class="gender-label">
                    <input type="radio" name="gender" value="MALE" required>
                    <span class="custom-radio"></span>
                    <span class="gender-text">남성</span>
                </label>
            </div>
            
            <label>키</label>
            <input type="number" name="height" required>
            
            <label>몸무게</label>
            <input type="number" name="weight" required>
			
			<label>하루 활동량</label>
			<select name="activityLevel" required>
			  <option value="1.2">매우 낮음 (거의 운동 안함)</option>
			  <option value="1.375">낮음 (주 1~3회 운동)</option>
			  <option value="1.55">보통 (주 3~5회 운동)</option>
			  <option value="1.725">높음 (주 6~7회 운동)</option>
			  <option value="1.9">매우 높음</option>
			</select>
			
			<label>나이</label>
			<input type="number" name="age" min="10" max="99" required>
			
            <!-- 목표 -->
            <label>목표</label>
            <div class="goal-row">
                <input type="date" name="startDate" required placeholder="시작일" class="goal-input">
                <input type="date" name="endDate" required placeholder="종료일" class="goal-input">
            </div>
            <div class="goal-row">
                <input type="number" name="goalWeight" placeholder="몇 kg? (예: 2)" required class="goal-input">
                <select name="goalType" required class="goal-input">
                    <option value="GAIN">증량</option>
                    <option value="LOSE">감량</option>
                </select>
            </div>
            
            <label>다짐</label>
            <input type="text" name="memo" placeholder="다짐을 적어봐요!" maxlength="100">
            
            <button type="submit" class="signup-btn">회원가입</button>
        </form>
    </div>
</body>
</html>
