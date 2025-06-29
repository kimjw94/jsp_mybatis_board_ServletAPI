<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2> 회원 탈퇴 절차</h2>
<form action="/withdrawMember" method="POST" >
    <input type="password" name="password" placeholder="비밀번호 재확인">
<button type="submit">탈퇴요청</button>
</form>




</body>
</html>