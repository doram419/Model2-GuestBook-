<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="oracle.OracleDAOImpl"%>
<%@page import="vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ServletContext context = getServletContext();
	// TODO: db테스트 이후 context 정보로 가져올 것 
	OracleDAOImpl dao = new OracleDAOImpl("himedia", "himedia"); 
	List<GuestBookVo> list = dao.getList();
	Iterator<GuestBookVo> iter = list.iterator();
%>
 
   
<!DOCTYPE html>

<html lang="kor">

<head>
	<meta charset="UTF-8">
	<title>방명록</title>
	<style>
		table {
			width: 70%;
		}
	
		table, tr, td{
			border-style: solid;
			border-color: black;
			border-width: 1px;
		}	
	</style>
	
	<script>
		function post(event, target){
			event.preventDefault(); 
			
			if(confirm("글을 올리시겠습니까?")){
				target.submit();
			}
		}
	</script>
</head>

<body>
	<form action= "<%=context.getContextPath()%>/add.jsp"
		method="POST" onsubmit="post(event, this)">
	</form>
	<table id="insertForm">
		<tr>
			<td>이름</td>
			<td>
				<input type="text" id="name" name="name"></input>
			</td>
			<td>비밀번호</td>
			<td></td>
		</tr>
		<tr>
			<td colspan = 4></td>
		</tr>
		<tr>
			<td colspan = 4> <button>작성</button> </td>
		<tr>
	</table>
	
	<br>
<% while(iter.hasNext()) { 
	GuestBookVo vo = iter.next();
%>
	<table class="guestBook">
		<tr>
			<td><%= vo.getNo() %></td>
			<td><%= vo.getName() %></td>
			<td><%= vo.getDate() %></td>
			<td><a href=#>삭제</a></td>
		</tr>
		<tr>
			<td colspan = 4><%= vo.getContent() %></td>
		</tr>
	</table>
	
	<br>
<% } %>
</body>

</html>