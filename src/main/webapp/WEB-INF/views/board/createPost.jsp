<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
</head>
<body>
<form action="/createPost" method="post" enctype="multipart/form-data">
    <!-- 사용자 정보 표시 (로그인 상태 기준) -->
    <div>
        <label><strong>작성자:</strong> <img src="resources/profileImageUpload/${sessionScope.LoginMember.profile_image}"
                                          width="30" height="30"> ${sessionScope.LoginMember.nickname}</label>
    </div>
    <br>
    <!-- 카테고리-->
    <label>카테고리:</label>
        <select name="board_category_id">
            <option value="1">웃긴자료</option>
            <option value="2"> 사진 </option>
            <option value="3"> 자유 게시판</option>
        </select>
    <!-- 제목 입력 -->
    <div>
        <label for="title">제목:</label><br>
        <input type="text" id="title" name="title" required style="width: 100%;">
    </div>
    <br>
    <!-- 본문 입력 -->
    <div>
        <label for="contents">내용:</label><br>
        <textarea id="contents" name="contents" rows="15" style="width: 100%;" required></textarea>
    </div>
    <br>

    <div>
        <input type="file" name="boardAttachement1">
    </div>
    <div>
        <input type="file" name="boardAttachement2">
    </div>
    <div>
        <input type="file" name="boardAttachement3">
    </div>
    <div>
        <input type="file" name="boardAttachement4">
    </div>
    <div>
        <input type="file" name="boardAttachement5">
    </div>


    <!-- 제출 버튼 -->
    <div>
        <button type="submit">작성 완료</button>
    </div>
</form>
</body>
</html>
