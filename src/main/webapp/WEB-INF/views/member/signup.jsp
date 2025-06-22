<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<form action="/signup" method="post" enctype="multipart/form-data">
    로그인 ID: <input type="text" name="login_id"> <br>
    Password: <input type="password" name="password"> <br>
    이름: <input type="text" name="name"> <br>
    성별: <label> 남자 <input type="radio" name="gender" value="M"></label>
    <label>여자<input type="radio" name="gender" value="F"></label> <br>

    닉네임: <input type="text" name="nickname"> <br>
    프로필 이미지 첨부: <input type="file" name="profile_image"> <br>

    이메일: <input type="text" name="email_first"> @
    <select name="email_domain" onchange="EnterDirectlyEmailDomain(this)">
        <option value="">--선택--</option>
        <option value="Enter_directly">직접입력</option>
        <option value="naver.com">naver.com</option>
        <option value="kakao.com">kakao.com</option>
        <option value="gmail.com">gmail.com</option>
    </select>
    <input type="text" name="email_domain_direct_enter" id="emailDomainInput" style="display: none;"
           placeholder="직접 입력"> <br>
    생년월일: <input type="date" name="birthday" id="birth">
    <button type="submit">회원가입</button>
</form>

<script>
    // 이메일 관련 select 옵션에서 직접 입력 선택 시 select 창 뒤에 input 보이게 하기
    const emailDomainInput = document.getElementById('emailDomainInput');

    function EnterDirectlyEmailDomain(selectElement) {
        if (selectElement.value === 'Enter_directly') {
            emailDomainInput.style.display = 'inline-block';
        } else {
            emailDomainInput.style.display = 'none';
            emailDomainInput.value = "";
        }
    }
</script>

</body>
</html>
