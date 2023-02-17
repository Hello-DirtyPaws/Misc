package com.upadhyay.suraj;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet(urlPatterns = {"/Servlet1", "/login.do"})
public class Servlet1 extends HttpServlet 
{
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("/WEB-INF/lib/Login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		
		if(username.length() != 0 && password.length() != 0)
		{
			session.setAttribute("time", new Date());
			session.setAttribute("username", username);
			req.getRequestDispatcher("/WEB-INF/lib/welcome.jsp").forward(req, resp);
		}
		else
		{
			req.setAttribute("err", "Invalid input!");
			req.getRequestDispatcher("/WEB-INF/lib/Login.jsp").forward(req, resp);
		}
	}
}
