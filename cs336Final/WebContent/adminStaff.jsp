<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>AS</title>
</head>
<body>
<%
String passwordParam = request.getParameter("password");
String emailParam = request.getParameter("email");
if(passwordParam.isEmpty() || emailParam.isEmpty()) {
out.println("Password or email field was empty. Please fill both in.");
} else {
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
	
String str = "SELECT password FROM administrativeStaff WHERE email=" + "'"+emailParam+"';" ;
ResultSet result = stmt.executeQuery(str);
if(result.next()){
String password = result.getString("password");
con.close();
if(password.equals(passwordParam)){
//String redirectURL = "http://localhost:8080/cs336Final/administrativeStaffPage.jsp";
    
session.setAttribute("username", emailParam);
response.sendRedirect("administrativeStaffPage.jsp");
out.println("<a href='administrativeStaffPage.jsp'>Head over to your Admin Page!</a>");
	
	
} 
else {
out.println("Password is not the same as confirm password");
	
}
} else {
out.println("Sorry! Your inputs were not in our system. Try again please!");
	
}
} catch (Exception ex) {
	out.println(ex);
	out.println("error");
}
}
	
	
%>
</body>
</html>