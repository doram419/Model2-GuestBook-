package himedia.myhome.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends BaseServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("home".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
		} else if("joinform".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/joinform.jsp");
			rd.forward(req, resp);
		} else if("joinsuccess.jsp".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/joinsuccess.jsp");
			rd.forward(req, resp);
		}
		else {
			resp.sendRedirect(req.getContextPath()+"/");
		}
		
		//super.doGet(req, resp);
	}
}
