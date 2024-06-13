package himedia.myhome.controller;

import java.io.IOException;

import himedia.myhome.dao.OracleDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/gb")
public class GuestBookServlet extends BaseServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("action");

		if("list".equals(actionName)) {
			OracleDAOImpl dao = new OracleDAOImpl(dbUser, dbPass);
			req.setAttribute("list", dao.getList());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			rd.forward(req, resp);
		}
		else {
			System.out.println("guestBookServlet:doGet:else");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("action");
		
		if("deleteform".equals(actionName)) { 
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(req, resp);
		}
		else if("delete".equals(actionName)) {			
			OracleDAOImpl dao = new OracleDAOImpl(dbUser, dbPass);
			
			if(req.getParameter("pass").equals(req.getParameter("passConfirm")))
			{
				Long no = Long.parseLong(req.getParameter("no"));
				dao.delete(no);
				
				// 테이블 불러오기는 따로 기능으로 빼도 괜찮겠는데?
				req.setAttribute("list", dao.getList());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
				rd.forward(req, resp);
				// TODO! 성공 실패 여부를 띄워야해!
				System.out.println("성공했습니다");
			}
			else
			{
				// TODO! 팝업으로 띄우고 싶어
				System.out.println("비밀번호가 일치하지 않습니다");
				// 테이블 불러오기는 따로 기능으로 빼도 괜찮겠는데?
				req.setAttribute("list", dao.getList());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
				rd.forward(req, resp);
			}
		}
		else if("add".equals(actionName)) {
			OracleDAOImpl dao = new OracleDAOImpl(dbUser, dbPass);
			String name = req.getParameter("name");
			String pass = req.getParameter("pass");
			String content = req.getParameter("content");
			
			dao.insert(name, pass, content);
			
			// 테이블 불러오기는 따로 기능으로 빼도 괜찮겠는데?
			req.setAttribute("list", dao.getList());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			rd.forward(req, resp);
		}
		else {
			System.out.println("guestBookServlet:doPost:else");
			//super.doPost(req, resp);
		}	
	}
	
	
}
