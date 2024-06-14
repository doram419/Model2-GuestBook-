package himedia.myhome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookie/get")
public class GetCookieServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; UTF-8");
		// 그냥 호출 -> 쿠키 확인
		// ?a=delete -> 쿠키
		PrintWriter out = resp.getWriter();
		
		String actionName = req.getParameter("a");
		
		if("delete".equals(actionName)) {
			// 쿠키 지우기
			// 	MaxAge -> 0 혹은 음수 값으로 변경, 무효화
			Cookie[] cookies = req.getCookies(); 
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("testCookie"))
					{
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
					}		
				}
				out.println("<h1>쿠키 삭제됨</h1>");
			}
		} else {
			// 요청 정보에서 쿠키 목록 확인 출력
			out.println("<h1>쿠키 목록</h1>");
			out.println("<ul>");
			
			Cookie[] cookies = req.getCookies(); 
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					out.printf("<h3>- %s -</h3><br>", cookie.getName());
					out.printf("<li>Value : %s</li>", 
							URLDecoder.decode(cookie.getValue(), "UTF-8"));
					out.printf("<li>MaxAge : %d</li>", cookie.getMaxAge());
				}
			}
			out.println("</ul>");
		}
			
		//super.doGet(req, resp);
	}
}
