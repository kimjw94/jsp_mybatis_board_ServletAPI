# 커뮤니티 사이트 (JSP + Spring MVC + MyBatis)

## 📌 개발 목적
- SI 기업에서 여전히 널리 사용되는 전통적인 웹 개발 구조 실습
  - `HttpServletRequest`, `HttpServletResponse`를 활용한 요청/응답 처리
  - `@RequestMapping`과 병행하여 사용하는 전통적인 방식 학습
  - Mapper 수동 설정을 통한 SQL 관리 경험
- JSP, Servlet, MyBatis 기반 MVC 아키텍처 게시판 구현

## 🛠️ 기술 스택
- Java, Spring MVC, JSP, MyBatis, Oracle DB, jQuery, AJAX

## 🚀 주요 기능
- 회원가입/로그인 (ID 중복 체크, 비밀번호 검증, 탈퇴 시 논리적 삭제 처리)
- 게시판 CRUD 및 댓글 시스템, **ROWNUM 기반 페이징 처리**
- 첨부파일 업로드/다운로드 (프로필 이미지, 게시글 첨부파일)
- AJAX 및 JavaScript 기반 실시간 유효성 검사

## 🎯 성과
- 실제 서비스 구조와 유사한 커뮤니티 사이트 구현
- 전통적인 MVC 아키텍처의 흐름을 직접 경험하고 유지보수성을 고려한 설계 능력 강화

## 📂 실행 방법
1. clone repo
2. DB 설정 (Oracle)
3. 서버 실행 (Tomcat)
