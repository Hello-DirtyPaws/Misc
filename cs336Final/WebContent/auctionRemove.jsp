<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Remove Auction</title>
</head>
<body>
<%
try{
int jewelryID = Integer.parseInt(request.getParameter("jewelryID"));
ApplicationDB DB = new ApplicationDB();	
Connection con = DB.getConnection();
String str = "DELETE FROM jewelry WHERE jewelryID = "+jewelryID+";";
PreparedStatement ps = con.prepareStatement(str);
ps.executeUpdate();
out.print("Successfully removed this auction. No one can bid on it anymore!");
} 
catch (Exception x){
out.print("Something prevented you from deleting this auction. Please try again.");
}
%>
<a href='customerRepresentativePage.jsp'>Back to Customer Representative Page</a>
</body>
</html>