# 커뮤니티 사이트 (JSP + Spring MVC + MyBatis)

## 📌 1. 개발 목적
- SI 기업에서 여전히 널리 사용되는 전통적인 웹 개발 구조 실습
- JSP, Servlet, MyBatis를 활용한 **MVC 아키텍처 기반 게시판 구현**
  
## ⚙️ 2. 개발 환경
- **Java**: 1.8  
- **Spring Boot**: 2.7.18  
- **Build Tool**: Maven  
- **DBMS**: Oracle Database 11g  
- **ORM/Mapper**: MyBatis (XML 기반 매핑)  
- **View**: JSP + JSTL  
- **IDE**: IntelliJ IDEA  
- **서버**: 내장 Tomcat (Spring Boot 실행 시 자동 포함)  

---

## 🏗️ 3. 구현 방식 (초기 버전)
- `HttpServletRequest`, `HttpServletResponse`를 통한 요청/응답 처리  
- `@RequestMapping`과 `HttpServletRequest`를 병행하여 사용하는 전통적인 방식  
- Mapper 수동 설정  
  - `SqlSessionFactoryBean`  
  - `SqlSessionTemplate`  
- `SqlSession` 객체를 `@Autowired`로 주입받고 `.getMapper()` 메서드를 통해 Mapper 인터페이스 사용  
- **파일 업로드**: `MultipartRequest` 활용 (cos 라이브러리 의존성 추가)  
- **로그인 기능**:  
  - 세션 기반 로그인  
---

## 🎨 4. View 구성
- JSP 기반 화면 구성  
- JSTL + EL(Expression Language) 활용하여 데이터 출력 처리  

---

## 🚀 주요 기능 요약
- 회원가입/로그인 (ID 중복체크, 비밀번호 검증, 탈퇴 시 논리 삭제 처리)  
- 게시판 CRUD + 댓글 기능  
- **ROWNUM 기반 페이징 처리**  
- 첨부파일 업로드/다운로드 (프로필, 게시글 첨부파일)  
- AJAX + JavaScript 기반 실시간 유효성 검사  

