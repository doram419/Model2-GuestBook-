<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ServletContext context = getServletContext();
%>
<!DOCTYPE html>

<html lang="kor">

<head>
	<meta charset="UTF-8">
	<title>Delete Form</title>
</head>

<body>
	<form action="<%=context.getContextPath()%>/gb?action=delete" method="POST">
		<label for="passConfirm">비밀번호 : </label>
		<input type="password" name="passConfirm" required/>
		<input type="hidden" name="pass" value="<%= request.getParameter("pass")%>"/>
		<input type="hidden" name="no" value="<%= request.getParameter("no")%>"/>
		<button>확인</button>
	</form>
	
	<p> <a href="<%=context.getContextPath()%>/gb?action=list"> 게시판으로 돌아가기</a> </p>
</body>

</html>