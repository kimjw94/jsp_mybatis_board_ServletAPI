<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" type="text/css" href="resources/css/withdrawMember.css"/>
</head>
<body>
<div class="withdrawMember_container">
    <span class="withdrawMember_title">회원 탈퇴</span>
    <hr class="main_divider">

    <div class="withdrawMember_notice">
        <p>회원 탈퇴를 진행하시면 회원님의 계정과 개인정보가 삭제되며, <span class="highlight">복구가 불가능합니다.</span></p>
        <p>탈퇴 후에는 동일한 아이디로 재가입이 제한될 수 있습니다.</p>
        <p>작성하신 게시글, 댓글 등 일부 콘텐츠는 법령 및 서비스 운영정책에 따라 탈퇴 후에도 유지될 수 있습니다.</p>
        <p>유료 서비스 이용권이나 포인트, 쿠폰 등은 탈퇴와 동시에 소멸되며 <span class="highlight">환불이 불가합니다.</span></p>
        <p>탈퇴를 원하실 경우, 모든 서비스 이용 내역을 확인하신 후 진행해주세요.</p>
    </div>

    <div class="withdrawMember_warning">
        ※ 정말 탈퇴를 진행하시겠습니까?
    </div>

    <form action="/withdrawMember" method="POST">
        <input type="password" name="password" placeholder="비밀번호 재확인" class="password_input">
        <button type="submit" class="withdraw_button">탈퇴요청</button>
    </form>
</div>
</body>
</html>