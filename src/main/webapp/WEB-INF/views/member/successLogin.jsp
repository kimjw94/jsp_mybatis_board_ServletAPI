<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="resources/css/successLogin.css">
</head>
<body>
<div class="successLogin_container">
    <img class="successLogin_profileImage"
         src="/resources/img/profileImageUpload/${sessionScope.LoginMember.profile_image}"/>
    <p>환영합니다.<br>
        ${sessionScope.LoginMember.nickname}님 </p>
    <div class="successLogin_btns">
        <a href="/getMemberInfo">마이페이지</a>
        <a href="/logout">로그아웃</a>
    </div>
</div>


</body>
</html>