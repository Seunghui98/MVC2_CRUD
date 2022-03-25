<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
<style>

</style>
</head>
<body>
	<h1>SSAFY 도서 사이트</h1>
	<c:if test="${empty userInfo }">
	<div style="float:right">
		<form name= "form" method="post" action="${root }/main">
			<input type="hidden" name="act" value="login">
			<label for="userid">로그인 : </label>
			<input type="text" id="userid" name="userid">
			<label for="userpwd">비밀번호 : </label>
			<input type="password" id="userpwd" name="userpwd">
			<button type="submit">로그인</button>
		</form>
	</div>
	</c:if>
	<c:if test="${!empty msg}">
		<script type="text/javascript">
		alert("${msg }");
		
		</script>
	</c:if>
	<c:if test="${!empty userInfo }">
	<div style="float:left">
		${userInfo.name}(${userInfo.id})님 안녕하세요.^^
		<a href="${root }/main?act=logout">로그아웃</a>
	</div>
	</c:if>
	<br>
	<br>
	<ul>
		<li><a href="${root }/main?act=mvregist">도서 등록</a>
		<li><a href="${root }/main?act=list">도서 목록</a> 
	</ul>
</body>
</html>