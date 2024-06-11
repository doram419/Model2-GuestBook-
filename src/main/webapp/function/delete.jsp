<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="oracle.OracleDAOImpl"%>
<%
ServletContext context = getServletContext();
String dbUser = context.getInitParameter("dbUser");
String dbPass = context.getInitParameter("dbPass");

OracleDAOImpl dao = new OracleDAOImpl(dbUser, dbPass);

if(dao.delete(Long.parseLong(request.getParameter("no")))) {
	response.sendRedirect(context.getContextPath()+"/views/list.jsp");
}
else
{
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "삭제하지 못했습니다.");
}

%>