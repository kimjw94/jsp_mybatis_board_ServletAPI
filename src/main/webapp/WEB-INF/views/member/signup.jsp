<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<link rel="stylesheet" href="resources/css/font.css">
<link rel="stylesheet" href="resources/css/signup.css">

<body>
<div class="signup_container">
    <span class="signup_title">회원 가입</span>
    <hr class="main_divider">

    <div class="signup_form">
        <form action="/signup" method="post" enctype="multipart/form-data" onsubmit="return validateFormChk(event);">

            <div class="signup_form_group">
                <label for="signup_login_id">로그인 ID</label>
                <input type="text" id="signup_login_id" name="login_id" oninput="validateIdChk()">
                <button type="button" onclick="checkLoginIdExists();" class="IdDuplicateBtn">중복 확인</button>
                <div class="signup_form_group_id_space"></div>
            </div>
            <div class="signup_login_id_guide">※아이디는 영문자(대/소문자) + 숫자 조합, 6~12자 이내로 입력</div>
            <div id="resultDiv_Id"></div>

                <hr class="signup_form_separator">

            <div class="signup_form_group">
                <label for="signup_password">비밀번호</label>
                <input type="password" id="signup_password" name="password" oninput="validatePasswordChk();">
                <div class="signup_form_group_space"></div>
            </div>
            <div class="signup_password_guide">※비밀번호는 8~16자, 영문 대/소문자·숫자·특수문자 중 2종 이상 조합 (공백 및 아이디 동일 불가)</div>
            <div id="resultDiv_Password"></div>

            <div class="signup_form_group">
                <label for="signup_passwordConfirm">비밀번호 확인</label>
                <input type="password" id="signup_passwordConfirm" oninput="validatePasswordConfirmChk();">
                <div class="signup_form_group_space"></div>
            </div>
            <div id="resultDiv_PasswordConfirm"></div>

                <hr class="signup_form_separator">

            <div class="signup_form_group">
                <label for="signup_name">이름</label>
                <input type="text" id="signup_name" name="name" oninput="validateNameChk();">
                <div class="signup_form_group_space"></div>
            </div>
            <div id="resultDiv_name"></div>

            <div class="signup_form_group">
                <label>성별</label>
                <div class="signup_radio_group">
                    <label class="radio_option">
                        <input type="radio" name="gender" value="M"> 남성
                    </label>
                    <label class="radio_option">
                        <input type="radio" name="gender" value="F"> 여성
                    </label>
                </div>
            </div>
            <div id="resultDiv_gender"></div>

            <div class="signup_form_group">
                <label>이메일</label>
                <div style="display: flex; gap: 5px; flex-wrap: wrap;">
                    <input type="text" id="signup_email_first" name="email_first" placeholder="이메일 앞부분" oninput="validateEmailFirstChk()">
                    <span>@</span>
                    <select name="email_domain" onchange="EnterDirectlyEmailDomain(this);validateEmailDomainChk();">
                        <option value="">--선택--</option>
                        <option value="Enter_directly">직접입력</option>
                        <option value="naver.com">naver.com</option>
                        <option value="kakao.com">kakao.com</option>
                        <option value="gmail.com">gmail.com</option>
                    </select>
                    <input type="text" name="email_domain_direct_enter" id="emailDomainInput"
                           style="display: none; margin-top: 5px;" class="signup_email_domain_direct_enter"
                           placeholder="직접 입력" oninput="validateEmailDomainChk();">
                </div>


            </div>
            <div id="resultDiv_email"></div>

            <div class="signup_form_group">
                <label for="signup_birthday">생년월일</label>
                <input type="date" id="signup_birthday" name="birthday" onchange="validateBirthdayChk();">
                <div class="signup_form_group_space"></div>
            </div>
            <div id="resultDiv_birthday"></div>

            <hr class="signup_form_separator">

            <div class="signup_form_group">
                <label for="signup_nickname">닉네임</label>
                <input type="text" id="signup_nickname" name="nickname" oninput="validateNicknameChk();">
                <button type="button" onclick="checkNicknameExists();" class="IdDuplicateBtn">중복 확인</button>
                <div class="signup_form_group_id_space"></div>
            </div>
            <div class="signup_nickname_guide">※닉네임은 3~10자 이내로 입력</div>
            <div id="resultDiv_nickname"></div>

            <hr class="signup_form_separator">

            <div class="signup_form_group">
                <label for="signup_profile_image">프로필 이미지 첨부</label>
                <input type="file" id="signup_profile_image" name="profile_image" onchange="validateProfileImageChk();" >
                <div class="signup_form_group_space"></div>
            </div>
            <div class="signup_profileImage_guide">※프로필 이미지는 필수 아님. jpg.png.gif 형식만 사용 가능, 이미지는 최대 10MB 이하 가능</div>
            <div id="resultDiv_profileImage"></div>

            <hr class="signup_form_separator">

            <div class="signup_form_group">
                <button type="submit">회원가입</button>
            </div>
        </form>
    </div>
</div>
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
<script type="text/javascript" src="resources/js/signup_validate.js"></script>
</body>
</html>
