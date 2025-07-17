<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>${post.title}</h2>

<p>
    작성자 ID:
    <img src="resources/profileImageUpload/${post.writer_profile_image}" width="100" height="100">
    ${post.writer_nickname}
</p>

<p>카테고리:
    <c:choose>
        <c:when test="${post.board_category_name eq 'funny'}">웃긴 자료</c:when>
        <c:when test="${post.board_category_name eq 'picture'}">사진 자료</c:when>
        <c:when test="${post.board_category_name eq 'freeTalk'}">자유 게시판</c:when>
        <c:otherwise>기타</c:otherwise>
    </c:choose>
</p>

<p>
    작성일시: ${post.created_at}<br>
    조회수: ${post.view_count} |
    추천: ${post.recommend} |
    비추천: ${post.not_recommend}
</p>

<hr>

<pre>${post.contents}</pre>

<!-- 추천/비추천 버튼은 추후 기능 추가 예정 -->
<button>추천</button>
<button>비추천</button>

<hr>
<!-- 댓글 표현 영역 -->

<!-- 댓글 리스트 출력 -->
<c:forEach var="c" items="${commentList}">
    <c:if test="${c.parent_id == null}">
        <!-- 댓글 -->
        <div class="comment" style="margin-bottom: 10px;">
            <div class="writer">
                <img src="/resources/profileImageUpload/${c.writer_profile_image}" alt="프로필" width="30" height="30">
                <strong>${c.writer_nickname}</strong>
            </div>
            <div class="comment-body">
                    ${c.comments}
            </div>
            <div class="comment-meta">
                <small>${c.created_at}</small>
            </div>

            <!-- 대댓글 리스트 -->
            <c:forEach var="r" items="${commentList}">
                <c:if test="${r.parent_id == c.board_comment_id}">
                    <div class="reply" style="margin-left: 40px; margin-top: 5px;">
                        <div class="writer">
                            <img src="/resources/profileImageUpload/${r.writer_profile_image}" alt="프로필" width="25" height="25">
                            <strong>${r.writer_nickname}</strong>
                        </div>
                        <div class="comment-body">
                                ${r.comments}
                        </div>
                        <div class="comment-meta">
                            <small>${r.created_at}</small>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</c:forEach>


<!-- 댓글 작성 영역 (후속 기능으로 확장 예정) -->
<div>
    <strong>댓글</strong>
    <form action="/createComment" method="POST">
        <input type="hidden" name="board_id" value="${post.board_id}">
        <textarea name="comments"></textarea>
        <button type="submit">등록</button>
    </form>
</div>

</body>
</html>