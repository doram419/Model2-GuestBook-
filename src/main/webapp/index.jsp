<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ServletContext context = getServletContext();
%>

<!DOCTYPE html>
<html lang="kor">

<head>
	<meta charset="UTF-8">
	<title>GuestBook Index Page</title>
</head>

<body>
	<h1>안녕하세요 목차페이지입니다</h1>
	<h3><a href="<%=context.getContextPath()%>/views/list.jsp">Oracle 게시판으로 가기</a></h3>
</body>

</html>