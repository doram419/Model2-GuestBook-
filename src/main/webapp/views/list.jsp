<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="oracle.OracleDAOImpl"%>
<%@page import="vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ServletContext context = getServletContext();
	String dbMgrName = context.getInitParameter("dbUser");
	String dbMgrPass = context.getInitParameter("dbPass");
	
	OracleDAOImpl dao = new OracleDAOImpl(dbMgrName, dbMgrPass); 
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
		
		function del(event, target) {
			event.preventDefault(); 
			
			if(confirm("정말 이 글을 삭제하시겠습니까?")){
				target.submit();
			}
		}
	</script>
</head>

<body>


	<table id="insertForm">
		<form action= "<%=context.getContextPath()%>/function/add.jsp"
		method="POST" onsubmit="post(event, this)">
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" required></input>
				</td>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pass" required></input>
				</td>
			</tr>
			<tr>
				<td colspan = 4>
					<!-- 클릭시 내용 초기화 넣기 -->
					<textarea cols="80" rows="5" name="content">게시판 규정과 맞지 않는 글은 삭제 될 수 있습니다.</textarea>
				</td>
			</tr>
			<tr>
				<td colspan = 4> <button>작성</button> </td>
			<tr>
		</form>
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
			<td>
				<form action= "<%=context.getContextPath()%>/function/delete.jsp"
						method="POST" onsubmit="del(event, this)">
				<input type="hidden" name="no" value="<%= vo.getNo()%>">
						<button>삭제</button>
						<!-- 버튼으로 일단 만들자 -->
				</form>
			</td>
		</tr>
		<tr>
			<td colspan = 4><%= vo.getContent() %></td>
		</tr>
	</table>
	
	<br>
<% } %>
</body>

</html>