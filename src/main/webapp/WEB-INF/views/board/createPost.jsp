<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <link rel="stylesheet" href="/resources/css/createPost.css"/>
</head>
<body>



    <form class="post_write_form" action="/createPost" method="post" enctype="multipart/form-data">
        <div id="createPost_container">
    <span class="createPost_title">
        게시글 등록
    </span>
            <hr class="main_divider">


        <div class="form_group writer_info">
            <label>
                <strong>작성자:</strong>
                <img src="resources/img/profileImageUpload/${sessionScope.LoginMember.profile_image}"
                     width="30" height="30" alt="프로필 이미지">
                <span>${sessionScope.LoginMember.nickname}</span>
            </label>
        </div>
        <br>

        <div class="form_group category_select">
            <label for="board_category_id">카테고리:</label>
            <select id="board_category_id" name="board_category_id" class="select_box">
                <option value="1">웃긴자료</option>
                <option value="2">사진</option>
                <option value="3">자유 게시판</option>
            </select>
        </div>
        <br>

        <div class="form_group title_input">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required class="input_field" placeholder="제목을 입력해 주세요.">
        </div>
        <br>

        <div class="form_group content_textarea">
            <label for="contents">내용:</label>
            <textarea id="contents" name="contents" rows="15" required class="textarea_field"
                      placeholder="내용을 입력해 주세요."></textarea>
        </div>
        <br>

        <div class="form_group file_upload">
            <label>파일 첨부:</label>
            <input type="file" name="boardAttachment1" class="file_input">
            <input type="file" name="boardAttachment2" class="file_input">
            <input type="file" name="boardAttachment3" class="file_input">
            <input type="file" name="boardAttachment4" class="file_input">
            <input type="file" name="boardAttachment5" class="file_input">
        </div>
        <br>

        <div class="form_group submit_button">
            <button type="submit" class="button_primary">작성 완료</button>
        </div>

    </form>
</div>
</body>
</html>