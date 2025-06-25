<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<a href="/signup">회원가입</a> <br>

<c:choose>
    <c:when test="${sessionScope.LoginMember == null}">
        <div>
            <span>로그인</span>
            <form action="/login" method="post">
                아이디: <input type="text" name="login_id"><br>
                비밀번호: <input type="password" name="password"><br>
                <button type="submit">로그인</button>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <a href="/logout">로그아웃</a> <br>
        <a href="/getMemberInfo">회원정보조회</a>
    </c:otherwise>
</c:choose>

</body>
</html>