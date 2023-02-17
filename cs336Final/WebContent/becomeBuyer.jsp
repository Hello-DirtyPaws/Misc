<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Become Seller</title>
</head>
<body>
<%
int jewelryID = Integer.parseInt(request.getParameter("id"));
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String email = ""+session.getAttribute("username");
String insert2 = "INSERT INTO Buyer values (?);";
PreparedStatement ps1 = con.prepareStatement(insert2);
ps1.setString(1, email);
ps1.executeUpdate();
response.sendRedirect("bid.jsp?id="+jewelryID);
%>
</body>
</html>