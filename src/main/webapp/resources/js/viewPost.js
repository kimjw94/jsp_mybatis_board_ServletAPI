function checkLoginAndSubmit(event) {
    if (isLoggedIn) {
        // 로그인 상태라면 폼을 제출
        return true;
    } else {
        // 로그인 상태가 아니라면
        alert("로그인이 필요합니다.");

        // 폼 제출을 막기 위해 false 반환
        return false;
    }
}