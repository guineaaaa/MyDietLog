<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MyDietLog 메인</title>
    <link rel="stylesheet" href="/static/css/main.css">
    <!-- dayjs로 실시간 한국시간 캘린더, 날짜 등 처리 가능 -->
    <script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/dayjs@1/locale/ko.js"></script>
</head>
<body>
<div class="main-container">
    <div class="navbar">
        <span class="logo"><a href="/" style="color:inherit;text-decoration:none;">MyDietLog</a></span>
        <div class="nav-links">
            <a href="/daily">daily</a>
            <a href="/weekly">weekly</a>
            <a href="/monthly">monthly</a>
            <a href="/goal">goal</a>
        </div>
        <button class="nav-btn" onclick="location.href='/mypage'">mypage</button>
    </div>

    <div class="content-grid">
        <div class="user-info">
            <div class="username">${loginUser.username}</div>
            <div class="goal">목표: <fmt:formatDate value="${goal.endDate}" pattern="M월 d일까지"/> ${goal.goalWeight}kg 
                <c:choose>
                    <c:when test="${goal.goalType eq 'GAIN'}">증량</c:when>
                    <c:otherwise>감량</c:otherwise>
                </c:choose>
            </div>
            <div class="memo">다짐: ${goal.memo}</div>
        </div>
        <div class="calendar-box">
            <div id="calendar"></div>
        </div>
        <div class="side-panel">
            <div class="progress-bar-box">
                <span class="progress-percent">13%</span>
                <div class="progress-bar-bg">
                    <div class="progress-bar-fill" style="width:13%"></div>
                </div>
            </div>
            <div class="diet-record-box">
                <div class="diet-title">식단기록 (<span id="selected-date">-</span>)</div>
                <form id="diet-form">
                    <div class="meal-record">
                        <div>아침</div>
                        <input type="text" name="breakfast_food" placeholder="음식명">
                        <input type="number" name="breakfast_kcal" placeholder="kcal">
                    </div>
                    <div class="meal-record">
                        <div>점심</div>
                        <input type="text" name="lunch_food" placeholder="음식명">
                        <input type="number" name="lunch_kcal" placeholder="kcal">
                    </div>
                    <div class="meal-record">
                        <div>저녁</div>
                        <input type="text" name="dinner_food" placeholder="음식명">
                        <input type="number" name="dinner_kcal" placeholder="kcal">
                    </div>
                    <button type="submit">등록</button>
                </form>
            </div>
            <div class="exercise-record-box">
                <div class="exercise-title">운동기록 (<span id="selected-date-ex">-</span>)</div>
                <form id="exercise-form">
                    <div class="exercise-row">
                        <input type="text" name="exercise_name" placeholder="운동명">
                        <select name="exercise_type_id">
                            <c:forEach var="type" items="${exerciseTypes}">
                                <option value="${type.id}">${type.type_name}</option>
                            </c:forEach>
                        </select>
                        <input type="number" name="calorie_burned" placeholder="소모 칼로리">
                    </div>
                    <button type="submit">등록</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 캘린더 JS 샘플 -->
<script>
    // 간단한 달력(캘린더) 생성: 실제론 라이브러리 FullCalendar 등 활용 권장
    function renderCalendar(year, month) {
        const today = dayjs();
        const firstDay = dayjs(`${year}-${month}-01`);
        const daysInMonth = firstDay.daysInMonth();
        let html = `<div class="calendar-title">${year}년 ${month}월</div><div class="calendar-grid">`;
        for (let d = 1; d <= daysInMonth; d++) {
            const dateStr = `${year}-${String(month).padStart(2, '0')}-${String(d).padStart(2, '0')}`;
            html += `<div class="calendar-cell" data-date="${dateStr}">${d}</div>`;
        }
        html += "</div>";
        document.getElementById("calendar").innerHTML = html;
        // 클릭 이벤트
        document.querySelectorAll('.calendar-cell').forEach(cell => {
            cell.onclick = function() {
                document.getElementById("selected-date").innerText = this.dataset.date;
                document.getElementById("selected-date-ex").innerText = this.dataset.date;
            };
        });
    }
    // 기본: 오늘 달력
    const now = dayjs().locale('ko');
    renderCalendar(now.year(), now.month()+1);
</script>
</body>
</html>
