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
    <!-- 왼쪽: 유저 정보 및 캘린더 -->
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
        <!-- 캘린더 -->
		<div class="calendar-area">
		    <div class="calendar-wrapper">
		        <div class="calendar-header">
		            <button id="prevMonth">◀</button>
		            <span id="calendar-year-month"></span>
		            <button id="nextMonth">▶</button>
		        </div>
		        <div class="calendar-label-row">
		            <div class="calendar-day-label">일</div>
		            <div class="calendar-day-label">월</div>
		            <div class="calendar-day-label">화</div>
		            <div class="calendar-day-label">수</div>
		            <div class="calendar-day-label">목</div>
		            <div class="calendar-day-label">금</div>
		            <div class="calendar-day-label">토</div>
		        </div>
		        <div id="calendar-body"></div>
		    </div>
		</div>
		<!-- main.jsp의 <script> 부분만 교체하세요 -->
			<!-- (중략, 위쪽 JSP 동일) -->
			<!-- (중략, 위쪽 JSP 동일) -->
			<script>
			    // Controller에서 받아온 selectedDate (yyyy-MM-dd)
			    // EL로 안전하게 처리 (혹시 null이면 오늘)
			    const selectedDateStr = '${selectedDate}';
			    // Date 객체로 변환 (JS에서 "yyyy-MM-dd"는 타임존 때문에 하루 차이 날 수 있음, 보정)
			    function toDate(str) {
			        // "2025-06-05" -> new Date("2025-06-05T00:00:00")
			        return str ? new Date(str + 'T00:00:00') : new Date();
			    }
			    let selectedDate = toDate(selectedDateStr);

			    // 달력 렌더링 기준 월/년
			    let currentYear = selectedDate.getFullYear();
			    let currentMonth = selectedDate.getMonth(); // 0~11

			    function renderCalendar(year, month) {
			        const calendarBody = document.getElementById('calendar-body');
			        calendarBody.innerHTML = ''; // 초기화

			        // 1. 첫 날짜, 마지막 날짜
			        const firstDay = new Date(year, month, 1);
			        const lastDay = new Date(year, month + 1, 0); // 이달 마지막 날

			        // 2. 요일 라벨이 이미 위에 있다고 가정(생략)
			        // 3. 몇 줄 그릴지 계산
			        let startDayOfWeek = firstDay.getDay(); // 0(일)~6(토)
			        let date = 1;

			        for (let row = 0; row < 6; row++) {
			            const weekRow = document.createElement('div');
			            weekRow.className = 'calendar-week-row';
			            for (let col = 0; col < 7; col++) {
			                const dayCell = document.createElement('div');
			                dayCell.className = 'calendar-day';

			                if (row === 0 && col < startDayOfWeek) {
			                    dayCell.innerHTML = ''; // 빈칸
			                } else if (date > lastDay.getDate()) {
			                    dayCell.innerHTML = ''; // 다음달 영역
			                } else {
			                    // 날짜 출력
			                    dayCell.textContent = date;

			                    // 오늘 or 선택한 날짜 표시
			                    const thisDate = new Date(year, month, date);
			                    // 오늘 표시
			                    const today = new Date();
			                    if (
			                        thisDate.getFullYear() === today.getFullYear() &&
			                        thisDate.getMonth() === today.getMonth() &&
			                        thisDate.getDate() === today.getDate()
			                    ) {
			                        dayCell.classList.add('today');
			                    }
			                    // 선택한 날짜 표시
			                    if (
			                        thisDate.getFullYear() === selectedDate.getFullYear() &&
			                        thisDate.getMonth() === selectedDate.getMonth() &&
			                        thisDate.getDate() === selectedDate.getDate()
			                    ) {
			                        dayCell.classList.add('selected');
			                    }
			                    // 날짜 클릭 이벤트: 오른쪽 식단/운동 영역 갱신 (or /main?date=...)
			                    dayCell.style.cursor = 'pointer';
			                    dayCell.onclick = function() {
			                        // 1. selectedDate 변수 갱신
			                        selectedDate = new Date(year, month, date);
			                        // 2. 달력 새로 그림(선택 강조)
			                        renderCalendar(year, month);

			                       
			                    };
			                    date++;
			                }
			                weekRow.appendChild(dayCell);
			            }
			            calendarBody.appendChild(weekRow);
			            if (date > lastDay.getDate()) break; // 끝까지 그리면 종료
			        }

			        // 상단 월/년 표시
			        document.getElementById('calendar-year-month').textContent =
			            year + '년 ' + (month + 1) + '월';
			    }

			    // yyyy-MM-dd 포맷 반환
			    function formatDate(d) {
			        let mm = d.getMonth() + 1;
			        let dd = d.getDate();
			        return (
			            d.getFullYear() +
			            '-' +
			            (mm < 10 ? '0' : '') + mm +
			            '-' +
			            (dd < 10 ? '0' : '') + dd
			        );
			    }

			    document.addEventListener('DOMContentLoaded', function() {
			        renderCalendar(currentYear, currentMonth);

			        document.getElementById('prevMonth').onclick = function() {
			            currentMonth--;
			            if (currentMonth < 0) {
			                currentMonth = 11;
			                currentYear--;
			            }
			            renderCalendar(currentYear, currentMonth);
			        };
			        document.getElementById('nextMonth').onclick = function() {
			            currentMonth++;
			            if (currentMonth > 11) {
			                currentMonth = 0;
			                currentYear++;
			            }
			            renderCalendar(currentYear, currentMonth);
			        };

			   
			    });
			</script>



    </div>

    <!-- 오른쪽: 식단, 운동 등록 폼 -->
    <div class="dash-right">
        <!-- 식단 등록 -->
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
        <!-- 운동 등록 -->
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
