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
	try
	{
		ApplicationDB db = new ApplicationDB();	
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		
		String str = "Select exists(select email, jewelryID from Auto where email = '" + 
					session.getAttribute("username") + "' and jewelryID =" + 
					Integer.parseInt(request.getParameter("id")) + ") as col;";
		ResultSet result = stmt.executeQuery(str);
		
		if(result.next())
		{
			//0 = Auto bidding for that particular person and jewelry does not exist.
			//1 = Auto bidding for that user on that jewelry ALREADY exist in the table.
			if(result.getInt(1) == 1)
			{
				out.print("Error! Your Auto Bid on this item already exists.<br>");
				out.print("<a href='jewelryPage.jsp?id=" + request.getParameter("id") + "&name=" + request.getParameter("name") + "'>Back</a>");
			}
			else
			{
				String act = "autoBidTester.jsp?id=" + request.getParameter("id") + "&name=" + request.getParameter("name"); %>
				<form method="post" action=<%=act%>>
				<table>
				<tr><td>Jewelry name: <font color = "green"> <% out.print(request.getParameter("name")); %></font></td></tr>
				<tr><td>Max Value: </td> <td> <input type="text" name="maxVal"></td></tr>
				<tr><td><input type="submit" value="Set Auto Bid" class="button"></td>
				<td><% out.print("<a href='jewelryPage.jsp?id=" + request.getParameter("id") + "&name=" + 
				request.getParameter("name") + "'>cancel</a>");%>
				</table>
				</form><%
			}
		}
		else
		{
			out.print("Error!");	
			out.print("<a href='jewelryPage.jsp?id=" + request.getParameter("id") + "&name=" + request.getParameter("name") + "'>Back</a>");
		}
		con.close();
	}
	catch(Exception e)
	{
		out.print("Error!");
		out.print("<a href='jewelryPage.jsp?id=" + request.getParameter("id") + "&name=" + request.getParameter("name") + "'>Back</a>");
	}
%>

</body>
</html>