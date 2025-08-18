<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="resources/css/memberinfo.css"/>
</head>
<body>
<div class="memberinfo_container">
    <span class="memberinfo_title">회원 정보</span>
    <hr class="main_divider">

    <div class="memberinfo_group">

        <div class="memberinfo_profile">
            <img class="memberinfo_profileImage" src="resources/img/profileImageUpload/${memberInfo.profile_image}">
            <p><strong>${memberInfo.nickname} 님</strong></p>
        </div>

        <div class="memberinfo_details">
            <div class="info_row">
                <label>이름</label>
                <span>${memberInfo.name}</span>
            </div>
            <div class="info_row">
                <label>아이디</label>
                <span>${memberInfo.login_id}</span>
            </div>
            <div class="info_row">
                <label>닉네임</label>
                <span>${memberInfo.nickname}</span>
            </div>
            <hr class="memberinfo_separator">

            <div class="info_row">
                <label>이메일</label>
                <span>${memberInfo.email}</span>
            </div>

            <div class="info_row">
                <label>성별</label>
                <c:choose>
                    <c:when test="${memberInfo.gender eq 'M'}">
                        <span> 남성</span>
                    </c:when>
                    <c:otherwise>
                        <span>여성</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="info_row">
                <label>생일</label>
                <span><fmt:formatDate value="${memberInfo.birthday}" pattern="yyyy년 MM월 dd일"/></span>
            </div>

            <hr class="memberinfo_separator">

            <div class="info_row">
                <label>가입일</label>
                <span><fmt:formatDate value="${memberInfo.created_at}" pattern="yyyy년 MM월 dd일"/></span>
            </div>

        </div>

    </div>
    <hr class="memberinfo_separator">
    <div class="memberinfo_actions">
        <a href="/updateMemberInfo" class="btn">회원 정보 수정</a>
        <a href="/withdrawMember" class="btn withdraw"> 회원 탈퇴</a>
    </div>
</div>

</body>
</html>