<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Auto Bid</title>
</head>
<body>
<%
int id = Integer.parseInt(request.getParameter("id"));
int maxVal = Integer.parseInt(request.getParameter("maxVal"));

//out.println("id = " + id + "\nmaxVal = " + maxVal + "\nemail = " + session.getAttribute("username").toString());

String email = session.getAttribute("username").toString();
try
{
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "Insert into Auto values('" + email + "'," + id + ", " + maxVal + ");";
PreparedStatement preparedStmt = con.prepareStatement(str);
preparedStmt.executeUpdate();

con.close();

out.println("The Auto bid is set up!");
}
catch (Exception ex) 
{
//out.println(ex);
out.println("error");
}
out.println("<p><a href='jewelryPage.jsp?id=" + request.getParameter("id") + "&name=" + request.getParameter("name") + "'>Back</a></p>");
out.println("<p><a href='dash.jsp'>Buyer Dashboard</a></p>");
out.println("<p><a href='logout.jsp'>Log out</a></p>");
%>

</body>
</html>