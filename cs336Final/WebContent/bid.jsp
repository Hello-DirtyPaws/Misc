<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>bid</title>
</head>
<body>
<%
int jewelryID = Integer.parseInt(request.getParameter("id"));
%>
<form method="post" action="bidPage.jsp?id=<%=jewelryID%>">
<table>	
<tr>
<td>Bid amount</td><td><input type="number" name="bidAmount" min="0"></td>
</tr>
				
</table>
<input type="submit" value="Bid on this item" class="button">
</form>
</body>
</html>