<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#book-list {
	border-collapse: collapse;
	width: 100%;
}

#book-list td, #book-list th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>도서 목록</h1>
	<table id="book-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>ISBN</th>
				<th>저자</th>
				<th>가격</th>
				<th>설명</th>
			</tr>
		</thead>
		<tbody>
<c:forEach var="book" items="${books}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><a href="${root}/main?act=detail&isbn=${book.isbn}">${book.isbn}</td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td>${book.desc}</td>

				</tr>
</c:forEach>			
		</tbody>
	</table>
</body>
</html>