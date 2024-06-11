<%@page import="oracle.OracleDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ServletContext context = getServletContext();
String dbUser = context.getInitParameter("dbUser");
String dbPass = context.getInitParameter("dbPass");

OracleDAOImpl dao = new OracleDAOImpl(dbUser, dbPass);

if(dao.insert(request.getParameter("name"), 
		request.getParameter("pass"), 
		request.getParameter("content"))) {
	response.sendRedirect(context.getContextPath()+"/views/list.jsp");
}
else
{
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "글을 추가하지 못했습니다.");
}

%>