package com.upadhyay.suraj;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet(urlPatterns = { "/Servlet2", "/moreinfo" },
			initParams= {@WebInitParam(name="defaultUser1", value="Suraj"),
						@WebInitParam(name="defaultUser2", value="Upadhyay")}
		   )
public class Servlet2 extends HttpServlet 
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		request.setAttribute("prof", request.getParameter("prof"));
		request.setAttribute("location", request.getParameter("location"));

		ServletConfig config = this.getServletConfig();
		request.setAttribute("defaultUser1", config.getInitParameter("defaultUser1"));
		request.setAttribute("defaultUser2", config.getInitParameter("defaultUser2"));
		
		request.getRequestDispatcher("/WEB-INF/lib/infoDisplay.jsp").forward(request, response);
		
	}

}
