# 🐤 MyDietLog
### SpringBoot+JSP 기반 나만의 식단 기록 & 영양 관리 웹서비스
![image](https://github.com/user-attachments/assets/694a5669-c510-479e-b74c-dc3c24a0e084)

# 🏗️ IA
![Flowchart](https://github.com/user-attachments/assets/c0f5fbd8-0017-4973-9aae-fc24cf506e0f)
# ✨ 주요 기능
- 회원가입/로그인/로그아웃
- 식단 기록 (아침/점심/저녁/기타), 칼로리 합산
- 운동 기록 (카테고리 분류), 칼로리 소모 합산
- 월별/일별/월간 Report 제공, Streak 및 출석 기록
- 권장 칼로리 설정
- JSP/Servlet + JSTL 기반의 Spring MVC 구조
# 🔨 Tech Stack
- Backend: Spring Boot 3.x (MVC 패턴)
- Frontend: JSP, JSTL, CSS, JS (정적 리소스)
- Database: MySQL
- ORM/DAO: Spring JDBC, 직접 구현 DAO
- Build/Deploy: Maven, WAR 패키징, 내장 Tomcat
- Java: 17
# 📁 프로젝트 구조
```
MyDietLog/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.demo/
│   │   │        ├── controller/        # Spring MVC 컨트롤러
│   │   │        ├── dao/               # DAO 및 DB접근
│   │   │        ├── model/             # VO/DTO/엔티티
│   │   │        └── service/           # 서비스 계층
│   │   ├── resources/
│   │   │    ├── static/                # 정적 리소스(
│   │   │    ├── templates/            
│   │   │    └── application.properties # 환경설정
│   │   └── webapp/
│   │        ├── static/
│   │        │    ├── css/              # CSS
│   │        │    └── img/              # 이미지
│   │        └── WEB-INF/
│   │             └── jsp/              # JSP View 파일
│   └── test/
│        └── java/                      # 테스트 코드
├── pom.xml                             # Maven 빌드 파일
└── README.md

```
# 🪣 ERD
![image](https://github.com/user-attachments/assets/e260985e-530a-4821-b823-7e4f7cee8f62)
