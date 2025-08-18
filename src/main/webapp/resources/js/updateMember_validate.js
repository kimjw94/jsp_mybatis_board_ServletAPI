$(function () {
    const validation = {
        isProfileImgValid: true,
        isPasswordValid: true,
        passwordChangeConfirmed: false, // 비밀번호 변경을 확인했는지 상태 추가
        isNicknameAvailable: false,
        originalNickname: $("#updateMember_nickname").val().trim()
    };

    $("#updateMember_profile_image").on("change", validateProfileImage);
    $("#updateMember_password").on("focus", confirmPasswordChange);
    $("#updateMember_password").on("input", validatePassword);
    $("#nicknameAvailableBtn").on("click", checkNicknameAvailability);
    $("#updateMember_nickname").on("input", function () {
        if ($(this).val().trim() !== validation.originalNickname) {
            validation.isNicknameAvailable = false;
            $("#resultDiv_nickname_update").text("닉네임 중복 확인이 필요합니다.").css("color", "red");
        } else {
            $("#resultDiv_nickname_update").text("");
        }
    });
    //제출하면
    $("#updateForm").on("submit", function (event) {
        validatePassword();

        const nickname = $("#updateMember_nickname").val().trim();
        const isNicknameUnchanged = (nickname === validation.originalNickname);

        if (!validation.isProfileImgValid || !validation.isPasswordValid || (!isNicknameUnchanged && !validation.isNicknameAvailable)) {
            alert("입력 정보를 다시 확인해주세요.");
            event.preventDefault();
        }
    });

    function confirmPasswordChange() {
        if (validation.passwordChangeConfirmed) {
            return;
        }

        if (confirm("비밀번호를 변경하시겠습니까?")) {
            validation.passwordChangeConfirmed = true;
        } else {
            $("#updateMember_password").blur();
        }
    }

    function validateProfileImage() {
        const file = this.files[0];
        const $resultDiv = $("#resultDiv_updateMember_profile_image");
        const allowedTypes = ["image/jpeg", "image/png", "image/gif"];
        const maxSize = 10 * 1024 * 1024;

        validation.isProfileImgValid = true;
        if (!file) {
            $resultDiv.text("");
            return;
        }

        if (!allowedTypes.includes(file.type)) {
            $resultDiv.text("jpg, png, gif 형식만 가능합니다.").css("color", "red");
            validation.isProfileImgValid = false;
        } else if (file.size > maxSize) {
            $resultDiv.text("이미지 크기는 10MB 이하만 가능합니다.").css("color", "red");
            validation.isProfileImgValid = false;
        } else {
            $resultDiv.text("사용 가능한 이미지입니다.").css("color", "blue");
        }
    }

    function validatePassword() {
        if (!validation.passwordChangeConfirmed) {
            return;
        }
        const password = $("#updateMember_password").val();
        const $resultDiv = $("#resultDiv_updateMember_password");
        if (password.trim() === "") {
            $resultDiv.text("비밀번호를 입력해주세요.").css("color", "red");
            validation.isPasswordValid = false;
            return;
        }
        const id = $("input[name='login_id']").val().trim();
        const pattern = /^(?:(?=.*[A-Za-z])(?=.*\d)|(?=.*[A-Za-z])(?=.*[^A-Za-z0-9])|(?=.*\d)(?=.*[^A-Za-z0-9])).+$/;

        validation.isPasswordValid = false;

        if (password.length < 8 || password.length > 16) {
            $resultDiv.text("비밀번호는 8~16자 이내로 입력해주세요.").css("color", "red");
        } else if (password === id) {
            $resultDiv.text("비밀번호는 아이디와 동일할 수 없습니다.").css("color", "red");
        } else if (/\s/.test(password)) {
            $resultDiv.text("비밀번호에는 공백을 포함할 수 없습니다.").css("color", "red");
        } else if (!pattern.test(password)) {
            $resultDiv.text("비밀번호는 영문/숫자/특수문자 중 2종 이상 조합이 되어야 합니다.").css("color", "red");
        } else {
            $resultDiv.text("사용 가능한 비밀번호입니다.").css("color", "blue");
            validation.isPasswordValid = true;
        }
    }

    function checkNicknameAvailability() {
        const nickname = $("#updateMember_nickname").val().trim();
        const $resultDiv = $("#resultDiv_nickname_update");

        if (nickname === validation.originalNickname) {
            $resultDiv.text("기존 닉네임과 동일합니다.").css("color", "gray");
            validation.isNicknameAvailable = true;
            return;
        }

        if (nickname.length < 3 || nickname.length > 10) {
            $resultDiv.text("닉네임은 3~10자로 입력해주세요.").css("color", "red");
            validation.isNicknameAvailable = false;
            return;
        }

        $.ajax({
            url: "/checkNicknameExists",
            type: "GET",
            data: {nickname: nickname},
            dataType: "json",
            success: function (response) {
                $resultDiv.text(response.message).css("color", response.checkNicknameExists ? "red" : "blue");
                validation.isNicknameAvailable = !response.checkNicknameExists;
            },
            error: function () {
                $resultDiv.text("중복 확인 중 오류가 발생했습니다.").css("color", "red");
                validation.isNicknameAvailable = false;
            }
        });
    }
});