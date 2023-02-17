<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Submit Post</title>
</head>
<body>

<% 
	String username = session.getAttribute("username").toString();
	String sub = request.getParameter("subject");
	String quest = request.getParameter("question");
	
	if(sub.length() == 0 || quest.length() == 0)
	{
		out.println("Error! The subject or the question feild can not be empty.<br>");
		out.println("<a href= \"question.jsp\">Back</a>");
		out.print("<a href=\"dash.jsp\">Dashboard</a>");
	}
	else
	{
		out.print("<p>Thank you. Your question is posted.</p><br>");
		out.print("<a href=\"dash.jsp\">Dashboard</a>");
		
		
		try{
			
			ApplicationDB db = new ApplicationDB();	
			
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			
			Timestamp duration = new Timestamp(System.currentTimeMillis());
			
			String insertCR = "INSERT INTO forum(customer_email, subject, question, datePosted) values (?, ?, ?, ?);";
			PreparedStatement ps = con.prepareStatement(insertCR);
			ps.setString(1, username);
			ps.setString(2, sub);
			ps.setString(3, quest);
			ps.setTimestamp(4, duration);
			ps.executeUpdate();
			con.close();
		} 
		catch (Exception xx)
		{
			out.println(xx);
		}
		
	}
%>



</body>
</html>