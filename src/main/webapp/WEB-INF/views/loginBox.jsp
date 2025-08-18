<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<link rel="stylesheet" href="resources/css/font.css">
<link rel="stylesheet" href="resources/css/loginPage.css">
<style>



</style>
<body>
<c:choose>
    <c:when test="${sessionScope.LoginMember == null}">
        <div class="login-wrapper">
            <div class="notLoggedIn">
                <span class="loginBox_header">로 그 인</span>
                <form action="/login" method="post">
                    <div class="login_form_group">
                        <label for="loginBox_login_id">아 이 디</label>
                        <input type="text" name="login_id" id="loginBox_login_id" required>
                    </div>

                    <div class="login_form_group">
                        <label for="loginBox_password">비 밀 번 호</label>
                        <input type="password" name="password" id="loginBox_password" required>
                    </div>
                    <div class="button-row">
                        <button class="loginBtn" type="submit">로 그 인</button>
                        <a class="button-link" href="/signup">회원가입</a>
                    </div>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="loggedIn">
            <a href="/logout">로그아웃</a> <br>
            <a href="/getMemberInfo">회원정보조회</a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>