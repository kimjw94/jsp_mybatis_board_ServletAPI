<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<div>
    <h2> 회원 정보</h2>
    <img src="resources/profileImageUpload/${memberInfo.profile_image}" width="100" height="100"><br>

    회원 이름: ${memberInfo.name} <br>
    회원 아이디: ${memberInfo.login_id}
</div>
<div>
    회원 이메일: ${memberInfo.email} <br>
    회원 닉네임: ${memberInfo.nickname}
</div>

<a href="updateMemberInfo">회원 정보 수정</a>
</body>
</html>