package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * GuestBook 프로젝트에서 서버 측에서 쓰이는 모든 Servlet들에 공통적인 부분을 처리하는 Servlet
 * */

public class BaseServlet extends HttpServlet{
	protected String dbUser;
	protected String dbPass;
	
	/**
	 * init(ServletConfig config) throws ServletException
	 * web.xml에 설정된 값을 로드하여 필드값을 초기화해주는 메서드 
	 * */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = getServletContext();
		dbUser = context.getInitParameter("dbUser");
		dbPass = context.getInitParameter("dbPass");
	}
}
