<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ServletContext context = getServletContext();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	List<GuestBookVo> list = null;
	Iterator<GuestBookVo> iter = null;
	
	if(request.getAttribute("list") instanceof List){
		list = (List<GuestBookVo>)request.getAttribute("list");
		iter = list.iterator();
	}
	else {
		System.out.println("정보를 불러오지 못했습니다.");
	}
%>
 
   
<!DOCTYPE html>

<html lang="kor">

<head>
	<meta charset="UTF-8">
	<title>방명록</title>
	<style>
		table {
			width: 100%;
		}
	
		table, tr, td{
			border-style: solid;
			border-color: black;
			border-width: 1px;
		}	
		
		td {
			witdh: 100%;
		}
		
		textarea {
			width = 99%;
		}
	</style>
	
	<script>	
		function del(event, target, no, pass) {
			event.preventDefault(); 
			
			if(confirm("정말 " + no +"번 글을 삭제하시겠습니까?")){
				let passConfirm = prompt("비밀번호를 입력하세요", ' ');
				
				if(passConfirm == pass)
					target.submit();
				else
					alert("비밀번호가 일치하지 않습니다!");
			}
		}
	</script>
</head>

<body>
	<h1>오라클 게시판</h1>
	<table id="insertForm">
		<form action= "<%=context.getContextPath()%>/gb?action=add"
		method="POST">
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
					<textarea cols="80" rows="5" name="content" onclick=this.value="">게시판 규정과 맞지 않는 글은 삭제 될 수 있습니다.</textarea>
				</td>
			</tr>
			<tr>
				<td colspan = 4> <button>작성</button> </td>
			<tr>
		</form>
	</table>
	
	<br>
<% 
	int index = 0;
	while(iter.hasNext()) { 
		GuestBookVo vo = iter.next();
		index++;
%>
	<table class="guestBook">
		<tr>
			<td><%= index %></td>
			<td>닉네임 : <%= vo.getName() %></td>
			<td>작성시간 :<%= sdf.format(vo.getDate()) %></td>
			<td>
				<form action= "<%=context.getContextPath()%>/gb?action=deleteform"
						method="POST">
						<input type="hidden" name="pass" value="<%= vo.getPass()%>">
						<input type="hidden" name="no" value="<%= vo.getNo()%>">
						<button>삭제</button>
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