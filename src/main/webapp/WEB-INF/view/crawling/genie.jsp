<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interpark Best Seller</title>
</head>
<body style="margin: 40px">
	<h3>Interpark Best Seller</h3>
	<hr>
	<table border="1">
		<tr>
			<th>순위</th>
			<th>이미지</th>
			<th>제목</th>
			<th>가수</th>
			<th>앨범명</th>
		</tr>
		<c:forEach var="f" items="${list}">
			<tr>
				<td>${f.rank}</td>
				<td><img src="${f.img}" height="38"></td>
				<td>${f.title}</td>
				<td>${f.artist}</td>
				<td>${f.album}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>