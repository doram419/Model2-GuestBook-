package himedia.myhome.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet{
	protected String dbUser = null;
	protected String dbPass = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// 서블릿 초기화
		ServletContext context = getServletContext();
		dbUser = context.getInitParameter("dbuser");
		dbPass = context.getInitParameter("dbpass");	
	}
}
