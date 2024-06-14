<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
    
<!DOCTYPE html>

<html lang="kor">

<head>
	<meta charset="UTF-8">
	<title>Session Read</title>
</head>

<body>
	<h3>Session 읽기</h3>
<%
String sess1 = null;
Integer sess2 = null;

if(session.getAttribute("sess1") instanceof String)
{
	sess1 = (String)session.getAttribute("sess1");
}

if(session.getAttribute("sess2") instanceof Integer)
{
	sess2 = (Integer)session.getAttribute("sess2");
}
%>
	<ul>
		<li>문자열 세션1 : <%= sess1 %></li>
		<li>정수 세션2 : <%= sess2 %></li>
	</ul>
	
	<p> <a href="session_delete.jsp">세션 삭제로 이동</a> </p>
	<p> <a href="session_write.jsp">세션 기록으로 이동</a> </p>
</body>

</html>