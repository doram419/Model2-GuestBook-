package himedia.myhome.controller;

import java.io.IOException;

import himedia.myhome.dao.UsersDao;
import himedia.myhome.dao.UsersDaoOracleImpl;
import himedia.myhome.vo.UserVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends BaseServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// action=joinform -> 가입 폼 페이지로 FORWARD
		// action=joinsuccess -> 가입 성공 페이지로 FORWARD
		String action = req.getParameter("action");
		
		if("joinform".equals(action)) {
			// 가입 폼으로 Forward
			RequestDispatcher rd 
				= getServletContext().getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		} else if("joinsuccess".equals(action)) {
			RequestDispatcher rd 
				= getServletContext().getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
		} else {
			// 아무것도 없으면 홈페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath()+"/");
		}
		
//		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// action=insert -> 회원가입
		String action = req.getParameter("action");
		if("join".equals(action)) {
			// 가입 처리
			String name = req.getParameter("name");
			String pass = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			UserVo vo = new UserVo(name, pass, email, gender);
			UsersDao dao = new UsersDaoOracleImpl(dbUser, dbPass);
			
			boolean success = dao.insert(vo);
			
			if(success) {
				resp.sendRedirect(req.getContextPath() + "/users?action=joinsuccess");
			} else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				System.out.println("실패했습니다");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		//super.doPost(req, resp);
	}
}
