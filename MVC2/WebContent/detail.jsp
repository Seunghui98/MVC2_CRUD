<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width:100%
}

th, td {
	border: 1px solid black;
}

th:nth-child(1){
	width: 100px;
}
</style>
</head>
<body>
	<h1>도서 정보</h1>
	<c:if test="${!empty book}">
		<table>
			<thead>
				<tr>
					<th>항목</th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td>도서번호</td>
				<td>${book.isbn }</td>
			</tr>
			
			<tr>
				<td>도서명</td>
				<td>${book.title }</td>
			</tr>
			<tr>
				<td>저자</td>
				<td>${book.author }</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${book.price }</td>
			</tr>
			<tr>
				<td>설명</td>
				<td>${book.desc }</td>
			</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty book}">
	<p>등록된 도서가 없습니다. ㅠㅠ</p>
	</c:if>
	<a href="${root}/main?act=mvupdate&isbn=${book.isbn}">도서 수정</a>
	<a href="${root}/main?act=delete&isbn=${book.isbn}">도서 삭제</a>
	<a href="${root}/main?act=mvregist">추가 등록</a>
	<a href="${root}/main?act=list">도서 목록</a>
	
</body>
</html>