<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page Title</title>
</head>
<body>
<h1>CRUD Test,Insert</h1>
<form action="/insert" method="post">
    <input type="text" name="test_id">
    <input type="text" name="test_name">
    <button type="submit">등록</button>
</form>

<h1>CRUD Test,Select</h1>
<form action="/selectById" method="post">
    id로 조회 <input type="text" name="test_id">
    <button type="submit"> 조회 </button>
</form>

<form action="/selectAllTest" method="post">
   이름으로 조회 <input type="text" name="test_name">
    <button type="submit"> 조회 </button>
</form>

<h1>CRUD Test,Update</h1>

<form action="/update" method="post">
    조회할 id : <input type="text" name="test_id">
    수정할 이름: <input type="text" name="test_name">
    <button type="submit">수정</button>
</form>

<h1>CRUD Test,Delete</h1>
<form action="/delete" method="post">
    삭제할 id: <input type="text" name="test_id">
    <button type="submit">삭제</button>
</form>



</body>
</html>
