<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Register</title>
</head>
<body>
<div id="box">
<form method="post" action="registerPage.jsp">
<table>
<tr>    
<td>Email: </td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>Password: </td><td><input type="password" name="password"></td>
</tr>
<tr>
<td>Confirm Password: </td><td><input type="password" name="passwordConfirm"></td>
</tr>
<tr>
<td>First Name: </td><td><input type="text" name="firstName"></td>
</tr>
<tr>
<td>Last Name: </td><td><input type="text" name="lastName"></td>
</tr>
<tr>
<td><input type="radio" name="seller" value="yes" /> Check if you are registering as a seller</td>
<td><input type="radio" name="seller" value="no" /> Check if you are registering as a buyer</td>
</tr>
<tr>
<td><p> Do not worry you can add on the one you did not chose later if you want to</p></td>
</tr>
</table>
<br>
<input type="submit" value="Register" class="button">
</form>
<br>
<form method="post" action="index.jsp">
<input type="submit" value="Back to log in" class="button">
</form>
</div>

</body>
</html>