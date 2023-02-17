<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>CustomerRepresentative</title>
</head>
<body>
<%
String passwordParam = request.getParameter("password");
String emailParam = request.getParameter("email");
if(passwordParam.isEmpty() || emailParam.isEmpty()) {
out.println("Your password or email field was empty. Please fill both of them in.");
} 
else {
try {
		
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "SELECT password FROM customerRepresentative WHERE email=" + "'"+emailParam+"';" ;
ResultSet result = stmt.executeQuery(str);
if(result.next()){
String password = result.getString("password");
con.close();
if(password.equals(passwordParam)){
session.setAttribute("username", emailParam);
response.sendRedirect("customerRepresentativePage.jsp");
out.println("<a href='customerRepresentativePage.jsp'>Hello customer representative! Click this link to head to your page!</a>");
	
	
} else {
out.println("Password does not match Database");
						
}
} else {
out.println("No data avaliable for that user");
			
}
} catch (Exception x) {
out.println(x);
out.println("I am sorry but we did not find your account.");
}
}	
%>
</body>
</html>