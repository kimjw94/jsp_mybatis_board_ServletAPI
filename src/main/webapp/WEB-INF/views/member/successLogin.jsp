<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<H1> 로그인 성공</H1>

<c:if test="${not empty sessionScope.LoginMember}">
    <p>환영합니다, ${sessionScope.LoginMember.nickname}님!</p>
</c:if>

<a href="/">홈</a>
<a href="/logout">로그아웃</a>
</body>
</html>