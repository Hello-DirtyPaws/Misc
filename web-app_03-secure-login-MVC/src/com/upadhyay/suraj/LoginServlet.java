package com.upadhyay.suraj;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.upadhyay.suraj.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/loginPage" })
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param req HttpServlet request
	 * @param res HttpServlet respone
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		LoginService loginService = new LoginService();
		
		if(loginService.authenticate(userId, password))
		{
			session.setAttribute("time", new Date());
			session.setAttribute("userId", userId);
			req.setAttribute("user", loginService.getUserDetail(userId));
			//resp.sendRedirect("welcome.jsp");
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		}
		else
		{
			req.setAttribute("err", "Invalid input!");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
