<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>데이터 전달</h1>
	<hr>
	<h3>파일 이름 : ${fileName }</h3>
	<h3>메세지 : ${message }</h3>
	<h3>과일목록</h3>
	<ul>
	<c:forEach var="f" items="${fruits }">
	<li>${f }</li>
	</c:forEach>
	</ul>
	<img src="/img/1209_01.png">
	
</body>
</html>