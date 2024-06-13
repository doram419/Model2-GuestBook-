<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<meta name="author" contetn="JHK">
	<title>My Home: Join Form</title>
	
	<link type="text/css" 
	rel="stylesheet" 
	href="<%= request.getContextPath() %>/css/home.css"/>
</head>

<body>
	<div id="container">
		<h1>Join Form</h1>
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		
		<div id="content">
			<form method="POST" action="<%= request.getContextPath()%>/users ">
				<input type="hidden" name="action" value="join" />
				<label for="name">이름</label>
				<input type="text" name="name" /><br/>
				<label for="password">패스워드</label>
				<input type="password" name="password" /><br/>
				<label for="email">이메일</label>
				<input type="text" name="email" /><br/>
				<label for="gender">성별</label>
				<input type="radio" name="gender" value="M" checked>남자
				<input type="radio" name="gender" value="F">여자
				<input type="submit" value="가입" />
			</form>
		</div>
		
		<!-- TODO: 폼 검증(Validation) -->
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>

</html>