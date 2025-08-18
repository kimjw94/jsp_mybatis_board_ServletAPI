const validationState = {
    isLoginIdAvailable: false,
    isLoginIdValidChk: false,
    isPasswordValidChk: false,
    isPasswordConfirmValidChk: false,
    isNameValidChk: false,
    isGenderValidChk: false,
    isEmailFirstValidChk: false,
    isEmailDomainSelectValidChk: false,
    isBirthdayValidChk: false,
    isNicknameValidChk: false,
    isNicknameAvailable: false,
    isProfileImageValidChk: false
}

//Id 관련 유효성 검사
function validateIdChk() {
    const $loginId = $("#signup_login_id");
    const loginIdVal = $loginId.val().trim();
    const $resultDiv_Id = $("#resultDiv_Id");
    const idPattern = /^[A-Za-z0-9]+$/;

    validationState.isLoginIdValidChk = false;
//1.빈 값 체크
    if (loginIdVal === "") {
        $resultDiv_Id.text("아이디를 입력해주세요").css("color", "red");
        validationState.isLoginIdValidChk = false;
//2.아이디 길이 체크(6~12자 이내)
    } else if (loginIdVal.length < 6 || loginIdVal.length > 12) {
        $resultDiv_Id.text("아이디는 6~12자 이내로 입력해주세요").css("color", "red");
        validationState.isLoginIdValidChk = false;
//3. 영문자+ 숫자 외 다른 문자 포함 여부 체크
    } else if (!idPattern.test(loginIdVal)) {
        $resultDiv_Id.text("아이디는 영문과 숫자 조합으로만 가능합니다.").css("color", "red");
        validationState.isLoginIdValidChk = false;
    } else if (validationState.isLoginIdAvailable === false) {
        $resultDiv_Id.text("아이디 중복 체크 확인이 필요 합니다.").css("color", "red");
    } else {
        $resultDiv_Id.text("사용 가능한 아이디 입니다.").css("color", "blue");
        validationState.isLoginIdValidChk = true;
    }
}

// jQuery 통한 AJAX 비동기 통신 - id 중복확인
function checkLoginIdExists() {
    const loginId = $("#signup_login_id").val().trim();
    const $resultDiv_Id = $("#resultDiv_Id");
    $.ajax({
        url: "/checkLoginIdExists",
        type: "GET",
        data: {login_id: loginId},
        dataType: "json",
        success: function (response) {
            console.log(response.message);
            $resultDiv_Id.text(response.message).css("color", response.checkLoginIdExists ? "red" : "blue")
            validationState.isLoginIdAvailable = !response.checkLoginIdExists;
        },
        error: function (xhr, status, error) {
            console.error("오류 발생:", status, error);
            validationState.isLoginIdAvailable = false;
        }
    });
}

//비밀번호 유효성 검사
function validatePasswordChk() {
    const $password = $("#signup_password");
    const passwordVal = $password.val().trim();
    const $resultDiv_Password = $("#resultDiv_Password");
    const passwordPattern = /^(?:(?=.*[A-Za-z])(?=.*\d)|(?=.*[A-Za-z])(?=.*[^A-Za-z0-9])|(?=.*\d)(?=.*[^A-Za-z0-9])).+$/;

    validationState.isPasswordValidChk = false;
    if (passwordVal === "") {
        $resultDiv_Password.text("비밀번호를 입력해주세요.").css("color", "red");
        validationState.isPasswordValidChk = false;

    } else if (passwordVal.length < 8 || passwordVal.length > 16) {
        $resultDiv_Password.text("비밀번호는 8~16자 이내로 입력해주세요.").css("color", "red");
        validationState.isPasswordValidChk = false;

    } else if (passwordVal === $("#signup_login_id").val().trim()) {
        $resultDiv_Password.text("비밀번호는 아이디와 동일할 수 없습니다.").css("color", "red");
        validationState.isPasswordValidChk = false;

    } else if (/\s/.test(passwordVal)) {
        $resultDiv_Password.text("비밀번호에는 공백을 포함할 수 없습니다.").css("color", "red");
        validationState.isPasswordValidChk = false;

    } else if (!passwordPattern.test(passwordVal)) {
        $resultDiv_Password.text("비밀번호는 영문/숫자/특수문자 중 2종 이상 조합이 되어야합니다.").css("color", "red");

    } else {
        $resultDiv_Password.text("사용 가능한 비밀번호 입니다.").css("color", "blue");
        validationState.isPasswordValidChk = true;

    }
}

//비밀번호 확인 체크

function validatePasswordConfirmChk() {
    const $password = $("#signup_password");
    const passwordVal = $password.val().trim();

    const $passwordConfirm = $("#signup_passwordConfirm");
    const passwordConfirmVal = $passwordConfirm.val().trim();

    const $resultDiv_PasswordConfirm = $("#resultDiv_PasswordConfirm");

    validationState.isPasswordConfirmValidChk = false;

    if (passwordConfirmVal === "") {
        $resultDiv_PasswordConfirm.text("비밀번호 확인 작성이 필요합니다.").css("color", "red");
        validationState.isPasswordConfirmValidChk = false;

    } else if (passwordVal !== passwordConfirmVal) {
        $resultDiv_PasswordConfirm.text("비밀번호와 일치하지 않습니다.").css("color", "red");
        validationState.isPasswordConfirmValidChk = false;

    } else {
        $resultDiv_PasswordConfirm.text("비밀번호와 일치합니다.").css("color", "blue");
        validationState.isPasswordConfirmValidChk = true;

    }
}

//이름 유효성 검사
function validateNameChk() {
    const $name = $("#signup_name");
    const nameVal = $name.val().trim();
    const $resultDiv_name = $("#resultDiv_name");

    validationState.isNameValidChk = false;
    if (nameVal === "") {
        $resultDiv_name.text("이름 입력이 필요합니다").css("color", "red");
        validationState.isNameValidChk = false;

    } else {
        validationState.isNameValidChk = true;
        $resultDiv_name.text("");

    }
}

//성별 유효성 검사 (이거는 form 제출 시 )

function validateGenderChk() {
    const genderVal = $("input[name='gender']:checked").val();
    const $resultDiv_gender = $("#resultDiv_gender");

    if (!genderVal) {
        $resultDiv_gender.text("성별을 체크해주세요").css("color", "red");
        validationState.isGenderValidChk = false

    } else {
        validationState.isGenderValidChk = true;
        $resultDiv_gender.text("");

    }
}

//이메일 함수 첫번째 부분 체크
function validateEmailFirstChk() {
    const $email_first = $("#signup_email_first");
    const email_first_val = $email_first.val().trim();

    const $result_Div_email = $("#resultDiv_email");

    validationState.isEmailFirstValidChk = false;

    $result_Div_email.text("");
//1. 앞 부분 체크
    if (email_first_val === "") {
        $result_Div_email.text("이메일 아이디를 입력해주세요").css("color", "red");
        validationState.isEmailFirstValidChk = false;

    } else {
        validationState.isEmailFirstValidChk = true;
        $result_Div_email.text("");

    }
}

//이메일 도메인 부분 체크
function validateEmailDomainChk() {
    const $email_domain = $("select[name='email_domain']");
    const email_domain_val = $email_domain.val();

    const $email_domain_input = $("#emailDomainInput");
    const email_domain_input_val = $email_domain_input.val().trim();

    const $result_Div_email = $("#resultDiv_email");
    validationState.isEmailDomainSelectValidChk = false;

//2. 도메인 부분 체크
    if (email_domain_val === "") {
        $result_Div_email.text("이메일 도메인 값 입력이 필요합니다.").css("color", "red");

        validationState.isEmailDomainSelectValidChk = false;

    } else if (email_domain_val === "Enter_directly" && email_domain_input_val === "") {
        $result_Div_email.text("이메일 도메인 값 직접 입력이 필요합니다.").css("color", "red");
        validationState.isEmailDomainSelectValidChk = false;

    } else {
        validationState.isEmailDomainSelectValidChk = true;
        $result_Div_email.text("");

    }
}

//생년월일
function validateBirthdayChk() {
    const $birthday = $("#signup_birthday");
    const birthdayVal = $birthday.val().trim();

    const $resultDiv_Birthday = $("#resultDiv_birthday");

    const today = new Date().toISOString().split("T")[0];
    validationState.isBirthdayValidChk = false;

    if (birthdayVal === "") {
        $resultDiv_Birthday.text("생년월일을 체크해야합니다").css("color", "red");
        validationState.isBirthdayValidChk = false;

    } else if (birthdayVal > today) {
        $resultDiv_Birthday.text("생년월일이 오늘보다 늦을 수는 없습니다.").css("color", "red");
        validationState.isBirthdayValidChk = false;

    } else {
        validationState.isBirthdayValidChk = true;
        $resultDiv_Birthday.text("");

    }
}

//닉네임
function validateNicknameChk() {
    const $nickname = $("#signup_nickname");
    const nicknameVal = $nickname.val().trim();

    const $resultDiv_nickname = $("#resultDiv_nickname");
    validationState.isNicknameValidChk = false;

    if (nicknameVal === "") {
        $resultDiv_nickname.text("닉네임을 입력해주세요.").css("color", "red");
        validationState.isNicknameValidChk = false;

    } else if (nicknameVal.length < 3 || nicknameVal.length > 10) {
        $resultDiv_nickname.text("닉네임은 3~10자이상으로 입력해주세요.").css("color", "red");
        validationState.isNicknameValidChk = false;

    } else if (validationState.isNicknameAvailable === false) {
        $resultDiv_nickname.text("닉네임 중복확인이 필요합니다.").css("color", "red");

    } else {
        validationState.isNicknameValidChk = true;
        $resultDiv_nickname.text("사용 가능한 아이디입니다.").css("color", "blue");

    }
}

// jQuery를 통한 비동기 통신- nickname 중복확인
function checkNicknameExists() {
    const nickname = $("#signup_nickname").val().trim();
    const $resultDiv = $("#resultDiv_nickname");

    $.ajax({
        url: "/checkNicknameExists",
        type: "GET",
        data: {nickname: nickname},
        dataType: "json",
        success: function (response) {
            console.log(response.message);
            $resultDiv.text(response.message).css("color", response.checkNicknameExists ? "red" : "blue")
            validationState.isNicknameAvailable = !response.checkNicknameExists;
        },
        error: function (xhr, status, error) {
            console.error("오류 발생:", status, error);
            validationState.isNicknameAvailable = false;
        }
    });

}

//프로필이미지
function validateProfileImageChk() {
    const $profileImage = $("#signup_profile_image");
    const $resultDiv_profileImage = $("#resultDiv_profileImage");

    validationState.isProfileImageValidChk = true;
    const file = $profileImage[0].files[0];
    const allowedTypes = ["image/jpeg", "image/png", "image/gif"];

    const maxSize = 10 * 1024 * 1024;
    if (!file) {
// 파일이 선택되지 않았으면 유효성 검사를 통과시킴 (선택은 필수가 아님)
        validationState.isProfileImageValidChk = true;
        $resultDiv_profileImage.text("");
        return;
    }
    if (!allowedTypes.includes(file.type)) {
        $resultDiv_profileImage.text("jpg, png, gif 형식만 가능합니다.").css("color", "red");
        validationState.isProfileImageValidChk = false;
    } else if (file.size > maxSize) {
        $resultDiv_profileImage.text("이미지 크기는 10MB 이하만 가능합니다.").css("color", "red");
        validationState.isProfileImageValidChk = false;
    } else {
        $resultDiv_profileImage.text("사용 가능한 이미지입니다.").css("color", "blue");
        validationState.isProfileImageValidChk = true;
    }
}

//제출 눌렀을때 전체 검사
function validateFormChk(event) {
    validateIdChk();
    validatePasswordChk();
    validatePasswordConfirmChk();
    validateNameChk();
    validateGenderChk();
    validateEmailFirstChk();
    validateEmailDomainChk();
    validateBirthdayChk();
    validateNicknameChk();
    validateProfileImageChk();

    for (let key in validationState) {
        if (!validationState[key]) {
            event.preventDefault()
            alert("항목을 다시 확인해주세요");
            return false;
        }
    }
}