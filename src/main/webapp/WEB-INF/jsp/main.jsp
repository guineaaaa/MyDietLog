<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDietLog - 메인</title>
    <link rel="stylesheet" href="/static/css/navbar.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <style>
        body {
            background: url('/static/img/bg.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Noto Sans KR', sans-serif;
            margin: 0;
            color: #4d3922;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="dashboard-container">
    <!-- 좌측: 유저 정보 및 캘린더 -->
    <div class="dash-left">
        <div class="user-summary">
            <div><b>${user.username}</b></div>
            <div>목표: <c:out value="${goal.endDate}" />까지 <c:out value="${goal.goalWeight}" />kg 
                <c:choose>
                    <c:when test="${goal.goalType == 'LOSE'}">감량</c:when>
                    <c:when test="${goal.goalType == 'GAIN'}">증량</c:when>
                </c:choose>
            </div>
            <div>다짐: <c:out value="${goal.memo}" /></div>
        </div>

        <!-- 캘린더 영역 -->
		<div class="calendar-area">
		  <div class="calendar-header">
		    <button id="prevMonth">◀</button>
		    <span id="calendar-year-month"></span>
		    <button id="nextMonth">▶</button>
		  </div>
		  <div class="calendar-grid">
		    <div class="calendar-day-label">일</div>
		    <div class="calendar-day-label">월</div>
		    <div class="calendar-day-label">화</div>
		    <div class="calendar-day-label">수</div>
		    <div class="calendar-day-label">목</div>
		    <div class="calendar-day-label">금</div>
		    <div class="calendar-day-label">토</div>
		  </div>
		  <div id="calendar-body" class="calendar-grid"></div>
		</div>

		<script>
		  const today = new Date(new Date().toLocaleString("en-US", { timeZone: "Asia/Seoul" }));
		  let selectedDate = "${selectedDate}";
		  let currentMonth = today.getMonth();
		  let currentYear = today.getFullYear();

		  function formatDate(date) {
		    const yyyy = date.getFullYear();
		    const mm = ('0' + (date.getMonth() + 1)).slice(-2);
		    const dd = ('0' + date.getDate()).slice(-2);
		    return `${yyyy}-${mm}-${dd}`;
		  }

		  function renderRealCalendar(year, month) {
		    const thisMonth = new Date(year, month, 1);
		    const lastDate = new Date(year, month + 1, 0).getDate();
		    const firstDay = thisMonth.getDay();

		    document.getElementById("calendar-year-month").innerText = `${year}년 ${month + 1}월`;

		    const calendarBody = document.getElementById("calendar-body");
		    calendarBody.innerHTML = "";

		    for (let i = 0; i < firstDay; i++) {
		      const emptyCell = document.createElement("div");
		      calendarBody.appendChild(emptyCell);
		    }

		    for (let d = 1; d <= lastDate; d++) {
		      const date = new Date(year, month, d);
		      const dateStr = formatDate(date);
		      const dayCell = document.createElement("div");
		      dayCell.className = "calendar-day";
		      dayCell.textContent = d;

		      if (dateStr === formatDate(today)) {
		        dayCell.classList.add("today");
		      }
		      if (dateStr === selectedDate) {
		        dayCell.classList.add("selected");
		      }

		      dayCell.addEventListener("click", () => {
		        selectedDate = dateStr;
		        document.querySelectorAll(".calendar-day").forEach(cell => cell.classList.remove("selected"));
		        dayCell.classList.add("selected");
		        document.querySelectorAll("input[name='date']").forEach(el => el.value = selectedDate);
		      });

		      calendarBody.appendChild(dayCell);
		    }
		  }

		  document.getElementById("prevMonth").addEventListener("click", () => {
		    currentMonth--;
		    if (currentMonth < 0) {
		      currentMonth = 11;
		      currentYear--;
		    }
		    renderRealCalendar(currentYear, currentMonth);
		  });

		  document.getElementById("nextMonth").addEventListener("click", () => {
		    currentMonth++;
		    if (currentMonth > 11) {
		      currentMonth = 0;
		      currentYear++;
		    }
		    renderRealCalendar(currentYear, currentMonth);
		  });

		  document.addEventListener("DOMContentLoaded", () => {
		    renderRealCalendar(currentYear, currentMonth);
		  });
		</script>

			

    <!-- 우측: 진행률, 식단, 운동 -->
    <div class="dash-right">
		<div class="record-form">
		  <form action="/dietlog/add" method="post">
		    <input type="hidden" name="date" value="${selectedDate}" />
		    <div class="record-label">식단 등록</div>
		    <label>아침</label>
		    <input type="text" name="breakfastFood" placeholder="음식 이름" required />
		    <input type="number" name="breakfastKcal" placeholder="칼로리" required />

		    <label>점심</label>
		    <input type="text" name="lunchFood" placeholder="음식 이름" required />
		    <input type="number" name="lunchKcal" placeholder="칼로리" required />

		    <label>저녁</label>
		    <input type="text" name="dinnerFood" placeholder="음식 이름" required />
		    <input type="number" name="dinnerKcal" placeholder="칼로리" required />

		    <button type="submit">식단 저장</button>
		  </form>
		</div>

		<!-- 운동 등록 폼 -->
		<div class="record-form">
		  <form action="/exerciselog/add" method="post">
		    <input type="hidden" name="date" value="${selectedDate}" />
		    <div class="record-label">운동 등록</div>
		    <label>운동명</label>
		    <input type="text" name="exerciseName" placeholder="운동명" required />

		    <label>운동 분류</label>
		    <select name="exerciseTypeId" required>
		      <option value="1">근력</option>
		      <option value="2">유산소</option>
		      <option value="3">스트레칭</option>
		      <!-- 필요 시 enum에 따라 옵션 확장 -->
		    </select>

		    <label>소모 칼로리</label>
		    <input type="number" name="calorieBurned" placeholder="칼로리" required />

		    <button type="submit">운동 저장</button>
		  </form>
		</div>
    </div>
</div>

</body>
</html>
