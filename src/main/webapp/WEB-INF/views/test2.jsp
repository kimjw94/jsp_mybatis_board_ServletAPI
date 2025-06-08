<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<p>${resultInsert}</p>

<p>${resultSelect.test_id}</p>
<p>${resultSelect.test_name}</p>

<c:if test="${not empty resultSelectAll}">
    <c:forEach var="dto" items="${resultSelectAll}">
        <p>ID: ${dto.test_id}</p>
        <p>이름: ${dto.test_name}</p>
        <hr>
    </c:forEach>
</c:if>

<p>${resultUpdate}</p>

<p>${resultDelete}</p>

</body>
</html>