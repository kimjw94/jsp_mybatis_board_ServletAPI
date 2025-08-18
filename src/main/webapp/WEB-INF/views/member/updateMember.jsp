<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<link rel="stylesheet" href="resources/css/updateMember.css">
<body>
<div class="updateMember_container">
    <div class="updateMember_title">
        <span>회원 정보 수정</span>
    </div>
    <hr class="main_divider">

    <form action="/updateMemberInfo" method="post" enctype="multipart/form-data" id="updateForm">
        <div class="updateMember_contents">

            <div class="updateMember_contents_left">
                <div class="updateMember_contents_details">
                    <label>현재 프로필 이미지</label>
                    <div>
                        <img src="resources/img/profileImageUpload/${memberInfo.profile_image}" width="100"
                             height="100">
                    </div>

                </div>

            </div>


            <div class="updateMember_contents_right">

                <div class="updateMember_contents_details">
                    <label>변경할 이미지 첨부하기</label>
                    <input type="file" name="profile_image" id="updateMember_profile_image"
                    >
                </div>
                <div id="resultDiv_updateMember_profile_image"></div>

                <div class="updateMember_contents_details">
                    <label>아이디</label>
                    <input type="text" name="login_id" value="${memberInfo.login_id}" readonly> <br>
                </div>

                <div class="updateMember_contents_details">
                    <label>비밀번호</label>
                    <input type="password" name="password" placeholder="변경 시에만 입력"
                           id="updateMember_password"
                    ><br>
                </div>
                <div id="resultDiv_updateMember_password"></div>

                <div class="updateMember_contents_details">
                    <label>닉네임</label>
                    <div class="nickname_wrapper">
                        <input id="updateMember_nickname" type="text" name="nickname" value="${memberInfo.nickname}">
                        <button type="button" id="nicknameAvailableBtn"> 중복 확인</button>
                    </div>
                </div>
                <div id="resultDiv_nickname_update"></div>


                <div class="updateMember_contents_details">
                    <label>이메일</label>
                    <input type="email" name="email" value="${memberInfo.email}"><br>
                </div>

                <button type="submit">정보 수정</button>
            </div>
        </div>
    </form>
</div>

<script src="resources/js/updateMember_validate.js"></script>
</body>
</html>