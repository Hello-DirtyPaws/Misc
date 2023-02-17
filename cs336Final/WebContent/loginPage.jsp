<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Dash</title>
</head>
<body>
<%
String passwordParam = request.getParameter("password");
String emailParam = request.getParameter("email");
if(passwordParam.isEmpty() || emailParam.isEmpty()) {
out.println("<h1>Password or email field was empty. Please fill both in.</h1>");
} 
else {
try { 	
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "SELECT password, firstName FROM customer WHERE email=" + "'"+emailParam+"';" ;
ResultSet result = stmt.executeQuery(str);
if(result.next()){
String password = result.getString("password");
String firstName = result.getString("firstName");
//int isSeller = result.getInt("isSeller");		
con.close();
if(password.equals(passwordParam)){
session.setAttribute("username", emailParam);
session.setAttribute("name", firstName);
//if(isSeller==0){
 //response.sendRedirect("dash.jsp");
 
out.println("<h1 >Choose a dashboard!</h1>");
out.println("<a href='dash.jsp'>Buyer Dashboard</a>");
out.println("<a href='sellerDash.jsp'>Seller Dashboard</a>");
//	}
//else{
		
//response.sendRedirect("sellerDash.jsp");
//}
						
} 
else {
out.println("Password does not match confirm password field");
						
}
} 
else {
out.println("Your email was enterred incorrectly or this username does not exist in BUYME.");
					
}
} catch (Exception x) {
out.println(x);
out.println("error");
}
}
%>
</body>
</html>
