<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>

<h1>WELCOME TO BUYME</h1>

<div id="box">
		
<form method="post" action="loginPage.jsp">
<table>
<tr >    
<td>Email: </td><td><input type="text" name="email"></td>
</tr>
<tr >
<td>Password: </td><td><input type="password" name="password"></td>
</tr>
</table>
<br>
<input type="submit" value="Customer Login" class="button">
</form>
<br>
<form method="post" action="register.jsp">
<input type="submit" value="Register" class="button">
</form>
<br>
<form method="get" action="passwordForgot.jsp">
<input type="submit" value="Forgot password?" class="button"><br>
</form>

<br>
<form id = "form" method="post" action="customerRep.jsp">
	
<table>
<tr>    
<td>CR Email: </td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>Password: </td><td><input type="password" name="password"></td>
</tr>
</table>
<br>
<input type="submit" value="Customer Representative Login" class="button">
	
	
</form>
<br>
<form method="post" action="adminStaff.jsp">
<table>
<tr>    
<td>AS Email: </td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>Password: </td><td><input type="password" name="password"></td>
</tr>
</table>
<br>
<input type="submit" value="Administrative Staff Login" class="button"><br>
</form>
</div>
</body>
</html>