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
<script>
	window.onload = function() {
		const form = document.getElementById('form');
		form.submit();
	}
</script>
<body style="margin: 40px">
	<h3>서울 소재 소방서 현황</h3>
	<hr>
	<img src="/img/loading.gif">
	<form Style="display: none" action="/cr/fireStation" method="post"
		id="form">
		<input type="hidden" name="dummy" value="0">
	</form>
</body>
</html>