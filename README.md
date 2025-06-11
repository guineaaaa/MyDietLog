# 🐤 MyDietLog
### SpringBoot+JSP 기반 나만의 식단 기록 & 영양 관리 웹서비스
- 1인 개발 팀 프로젝트
- 프로젝트 진행 기간: 2~3주
  
![image](https://github.com/user-attachments/assets/694a5669-c510-479e-b74c-dc3c24a0e084)
# 🏗️ IA
![Flowchart](https://github.com/user-attachments/assets/c0f5fbd8-0017-4973-9aae-fc24cf506e0f)
# ✨ 주요 기능
- 회원가입/로그인/로그아웃
- 목표 및 사용자 맞춤 일일 권장 칼로리 계산
- 월별/일별/월간 Report 제공, Streak 및 출석 기록
- 식단 기록 (아침/점심/저녁/기타), 칼로리 합산
- 운동 기록 (카테고리 분류), 칼로리 소모 합산
- JSP/Servlet + JSTL 기반의 Spring MVC 구조
# 🔨 Tech Stack
- `Backend`: Spring Boot 3.x (MVC 패턴)
- `Frontend`: JSP, JSTL, CSS, JS (정적 리소스)
- `Database`: MySQL
- `ORM/DAO`: Spring JDBC, 직접 구현 DAO
- `Build/Deploy`: Maven, WAR 패키징, 내장 Tomcat
- `Java`: 17
# 📁 프로젝트 구조
```
MyDietLog/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.demo/
│   │   │        ├── controller/           # Spring MVC 컨트롤러 (웹/REST)
│   │   │        ├── dao/                  # DAO, Repository (DB 접근)
│   │   │        ├── model/                # Entity 
│   │   │        │    ├── dto/             # DTO(전달용, API/뷰전송)
│   │   │        │    ├── enums/           # Enum(상태값, 타입명 등)
│   │   │        └── service/              # 서비스 계층(비즈니스 로직)
│   │   ├── resources/
│   │   │    ├── static/                   # 정적 파일(css, js, img)
│   │   │    ├── templates/                # Thymeleaf, 템플릿 엔진 쓸 때
│   │   │    └── application.properties    # Spring 설정
│   │   └── webapp/
│   │        ├── static/
│   │        │    ├── css/                 # CSS 파일
│   │        │    └── img/                 # 이미지 파일
│   │        └── WEB-INF/
│   │             └── jsp/                 # JSP View 파일
│   └── test/
│        └── java/                         # 단위/통합테스트
├── pom.xml                                # Maven 빌드
└── README.md
```
# 🪣 ERD
![springfinal](https://github.com/user-attachments/assets/a0ce3506-3b01-4556-899e-9068ae3664cb)
