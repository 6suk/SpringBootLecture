<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fire Station</title>
</head>
<body style="margin: 40px">
	<h3>서울 소재 소방서 현황</h3>
	<hr>
	<table border="1">
		<tr>
			<th>no.</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
		</tr>
		<c:forEach var="f" items="${list}" varStatus="s" >
			<tr>
				<td>${s.count }</td>
				<td>${f.name}</td>
				<td>${f.addr}</td>
				<td>${f.tel}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>