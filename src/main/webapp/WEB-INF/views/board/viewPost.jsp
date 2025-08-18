<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/resources/css/viewPost.css"/>
    <script>  const isLoggedIn =
    ${not empty sessionScope.LoginMember}
    </script>

</head>
<body>
<div class="post_container">

    <div class="post_header">
        <h2 class="post_title">${post.title}</h2>
        <div class="post_meta">
                <span class="writer_info">
                    <img src="/resources/img/profileImageUpload/${post.writer_profile_image}" width="30" height="30"
                         alt="프로필">
                    ${post.writer_nickname}
                </span>
            <span class="category_info">
                    카테고리:
                    <c:choose>
                        <c:when test="${post.board_category_name eq 'funny'}">웃긴 자료</c:when>
                        <c:when test="${post.board_category_name eq 'picture'}">사진 자료</c:when>
                        <c:when test="${post.board_category_name eq 'freeTalk'}">자유 게시판</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </span>
            <p>
                <small>작성일시: <fmt:formatDate value="${post.created_at}" pattern="yyyy년 MM월 dd일 HH:mm"/></small> |
                <small>조회수: ${post.view_count}</small> |
                <small>추천: ${post.recommend}</small> |
                <small>비추천: ${post.not_recommend}</small>
            </p>
        </div>
        <hr class="post_divider">
    </div>

    <c:if test="${not empty post.attachments}">
        <div class="post_attachments">
            <c:forEach var="attachment" items="${post.attachments}">
                <div class="attachment_item">
                    <c:set var="fileName" value="${attachment.file_name}" />


                    <c:if test="${fileName.endsWith('.jpg') or fileName.endsWith('.jpeg') or fileName.endsWith('.png') or fileName.endsWith('.gif')}">
                        <img src="/resources/img/boardAttachment/${fileName}" alt="첨부 이미지">
                    </c:if>

                    <c:if test="${not (fileName.endsWith('.jpg') or fileName.endsWith('.jpeg') or fileName.endsWith('.png') or fileName.endsWith('.gif'))}">
                        <a href="/resources/img/boardAttachment/${fileName}" download>${fileName}</a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>


    <div class="post_content">
        <pre>${post.contents}</pre>


    </div>





    <div class="post_actions">
        <button class="button_secondary">추천</button>
        <button class="button_secondary">비추천</button>
    </div>

    <hr>

    <div class="comment_section">
        <h3 class="comment_title">댓글</h3>

        <c:forEach var="c" items="${commentList}">
            <c:if test="${c.parent_id == null}">
                <div class="comment">
                    <div class="comment_writer">
                        <img src="/resources/img/profileImageUpload/${c.writer_profile_image}" alt="프로필" width="30"
                             height="30">
                        <strong>${c.writer_nickname}</strong>
                    </div>
                    <div class="comment_body">
                            ${c.comments}
                    </div>
                    <div class="comment_meta">
                        <small>${c.created_at}</small>
                    </div>
                    <c:forEach var="r" items="${commentList}">
                        <c:if test="${r.parent_id == c.board_comment_id}">
                            <div class="reply">
                                <div class="comment_writer">
                                    <img src="/resources/img/profileImageUpload/${r.writer_profile_image}" alt="프로필"
                                         width="25" height="25">
                                    <strong>${r.writer_nickname}</strong>
                                </div>
                                <div class="comment_body">
                                        ${r.comments}
                                </div>
                                <div class="comment_meta">
                                    <small>${r.created_at}</small>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
        </c:forEach>


        <div class="comment_form_container">
            <form action="/createComment" method="POST" onsubmit="return checkLoginAndSubmit(event);">
                <input type="hidden" name="board_id" value="${post.board_id}">
                <textarea name="comments" class="comment_textarea" placeholder="댓글을 입력해 주세요."></textarea>
                <button type="submit" class="button_primary">등록</button>
            </form>
        </div>

    </div>
</div>
<script src="/resources/js/viewPost.js"></script>
</body>
</html>