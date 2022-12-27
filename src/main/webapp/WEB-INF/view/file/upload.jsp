<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="/style.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="inputtb content col-lg-8">
		<!-- 타이틀 -->
		<div class="inputtb content-title pb-4">
			<h3>게시글 쓰기</h3>
			<div class="">
				<button class="btn small subcolor"
					onclick="location.href='#'">&lt;
					List</button>
			</div>
		</div>
		<!-- 타이틀 끝 -->

		<form action="/file/upload" class="pt-4 mx-3" method="post"
			enctype="multipart/form-data">
			<table class="inputtb board-desc">
<!-- 				<tr>
					<td><input class="board-input" type="text" placeholder="제목"
						name="title" maxlength="128" required /></td>
				</tr>
				<tr>
					<td><textarea class="board-input" name="content"
							placeholder="내용" maxlength="5000" rows="10"></textarea></td>
				</tr> -->
				<tr>
					<td><input class="board-input" type="file" placeholder="첨부파일1"
						name="files" multiple /></td>
				</tr>
				<tr>
					<td colspan="2" class="multibtn pt-4"><input type="submit"
						class="btn full maincolor" value="글쓰기" />
						<div class="space10"></div> <input type="reset"
						class="btn full subcolor" value="취소" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>