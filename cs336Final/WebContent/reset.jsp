<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>reset</title>
</head>
<body>
<%
try{
String emailParam = request.getParameter("email");
String newPasswordParam = request.getParameter("password");
ApplicationDB DB = new ApplicationDB();	
Connection con = DB.getConnection();
String str = "UPDATE customer SET password='"+newPasswordParam+"' where email = '"+emailParam+"';";
PreparedStatement ps = con.prepareStatement(str);
ps.executeUpdate();
out.println("Successfully reset the customer's password");
} catch (Exception x){
out.println("The password was too many characters. You cannot reset it to the requested new password");
}
%>
<a href='customerRepresentativePage.jsp'>Back to Customer Representative Page</a>
</body>
</html>