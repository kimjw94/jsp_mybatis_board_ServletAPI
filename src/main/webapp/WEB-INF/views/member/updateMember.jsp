<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>회원 정보 수정</h2>
<form action="/updateMemberInfo" method="post" enctype="multipart/form-data">
    <div>
        현재 프로필 이미지: <img src="resources/profileImageUpload/${memberInfo.profile_image}" width="100" height="100"><br>
    </div>
    변경할 이미지 첨부하기: <input type="file" name="profile_image"> <br>
    아이디: <input type="text" name="login_id" value="${memberInfo.login_id}" readonly> <br>
    비밀번호: <input type="password" name="password" placeholder="변경 시에만 입력"><br>
    닉네임: <input type="text" name="nickname" value="${memberInfo.nickname}"><br>
    이메일: <input type="email" name="email" value="${memberInfo.email}"><br>
    <button type="submit">정보 수정</button>
</form>

</body>
</html>