function checkLoginAndRedirect() {
    if (isLoggedIn) {
        window.location.href = "/createPost";
    } else {
        alert("로그인이 필요합니다.");
    }
}