<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>

<a href="/writeBoard">게시글 작성</a>

<table>
    <thead>
    <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty boardList}">
            <c:forEach var="b" items="${boardList}">
                <tr>
                    <td>${b.title} (추천: ${b.recommend}, 비추천: ${b.not_recommend})</td>
                    <td>
                        <img src="resources/profileImageUpload/${b.writer_profile_image}" width="20" height="20">
                            ${b.writer_nickname}
                    </td>
                    <td>${b.created_at}</td>
                    <td>${b.view_count}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4">게시글이 없습니다.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<!-- ✅ 페이징 바: 게시글이 있을 때만 출력 -->
<c:if test="${not empty boardList}">
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>[${i}]</strong>
                </c:when>
                <c:otherwise>
                    <a href="/board?category_id=${param.category_id}&page=${i}">[${i}]</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
