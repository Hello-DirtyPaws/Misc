<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Forgot Password Page</title>
</head>
<body>
<div>
<h1>Keep your password under 45 characters</h1>
<form method="post" action="passwordForgotPage.jsp">
<table>
<tr>    
<td>Email: </td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>Enter New Password: </td><td><input type="password" name="password"></td>
</tr>
</table>
<br>
<input type="submit" value="submit" class="button">
</form>
</div>
</body>
</html>