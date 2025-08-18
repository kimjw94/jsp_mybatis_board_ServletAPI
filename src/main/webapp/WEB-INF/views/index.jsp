<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="resources/css/font.css">
    <link rel="stylesheet" href="resources/css/index.css">

    <script type="text/javascript" src="resources/js/jQuery.js"></script>
</head>
<body>
<header class="header">
    <div class="header_inner">
        <div class="header_logo">
            <a href="/"><img src="resources/img/mainLogo/logo.png"></a>
        </div>
        <div class="header_nav_links">
            <a href="/board?category_id=1">웃긴자료</a>
            <a href="/board?category_id=2">사진</a>
            <a href="/board?category_id=3">자유 게시판</a>
        </div>
    </div>
</header>
<hr class="DivisionLine">
<div class="container">
    <div class="loginPage">
        <jsp:include page="${lp}"/>
    </div>

    <div class="sitePage">
        <jsp:include page="${cp}"/>
    </div>
</div>

<hr class="DivisionLine">
<footer class="footer">
    © 2025 AGORA. All rights reserved.
</footer>
</body>
</html>