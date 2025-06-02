<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog - Main</title>
    <link rel="stylesheet" href="/static/css/mainpage.css">
	<link rel="stylesheet" href="/static/css/navbar.css?v=1.0"
</head>
<body>
	<%@ include file="navbar.jsp" %>  
        <div class="main-content">
            <!-- 좌측: 캘린더 -->
            <div class="calendar-section">
                <div class="user-info">
                    <div>사용자명</div>
                    <div>목표: <b>7월 5일까지 2kg 감량</b></div>
                    <div>다짐: <span>나도 할 수 있다</span></div>
                </div>
                <!-- TODO: JS 캘린더 플러그인/직접구현 자리 -->
                <div class="calendar-box">
                    <!-- 예시: "오늘"은 .today, 선택일은 .selected, JS로 처리 -->
                    <table class="calendar">
                        <tr>
                            <th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
                        </tr>
                        <!-- 반복문으로 각 주차 구성 -->
                        <tr>
                            <td class="today">2</td>
                            <td>3</td>
                            <td class="selected">4</td>
                            <td>5</td>
                            <td>6</td>
                            <td>7</td>
                            <td>8</td>
                        </tr>
                        <!-- ... -->
                    </table>
                </div>
            </div>
            
            <!-- 우측: 목표진행, 식단/운동 입력, 리스트 -->
            <div class="log-section">
                <!-- 목표 진행률 -->
                <div class="goal-progress">
                    <div class="progress-label">
                        <span>13%</span>
                        <div class="progress-bar-wrap">
                            <div class="progress-bar" style="width: 13%;"></div>
                        </div>
                    </div>
                </div>
                
                <!-- 식단기록 -->
                <div class="log-box">
                    <div class="log-label">식단기록</div>
                    <table class="meal-table">
                        <tr>
                            <th>구분</th>
                            <th>음식명</th>
                            <th>칼로리(kcal)</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>아침</td>
                            <td><input type="text" name="breakfastFood"></td>
                            <td><input type="number" name="breakfastCal"></td>
                            <td><button>등록</button></td>
                        </tr>
                        <tr>
                            <td>점심</td>
                            <td><input type="text" name="lunchFood"></td>
                            <td><input type="number" name="lunchCal"></td>
                            <td><button>등록</button></td>
                        </tr>
                        <tr>
                            <td>저녁</td>
                            <td><input type="text" name="dinnerFood"></td>
                            <td><input type="number" name="dinnerCal"></td>
                            <td><button>등록</button></td>
                        </tr>
                    </table>
                </div>
                
                <!-- 운동기록 -->
                <div class="log-box">
                    <div class="log-label">운동기록</div>
                    <table class="exercise-table">
                        <tr>
                            <th>운동명</th>
                            <th>종류</th>
                            <th>칼로리</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td><input type="text" name="exerciseName"></td>
                            <td>
                                <select name="exerciseType">
                                    <option value="CARDIO">유산소</option>
                                    <option value="STRENGTH">근력</option>
                                    <option value="STRETCH">스트레칭</option>
                                    <option value="HIIT">고강도</option>
                                    <option value="YOGA">요가</option>
                                    <option value="PILATES">필라테스</option>
                                    <option value="SPORTS">스포츠</option>
                                    <option value="ETC">기타</option>
                                </select>
                            </td>
                            <td><input type="number" name="exerciseCal"></td>
                            <td><button>등록</button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
