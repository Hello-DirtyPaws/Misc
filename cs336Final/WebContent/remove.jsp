<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>remove</title>
</head>
<body>
<%

//Find next highest value in cost column for bid for jewelryId. 
///set that value to the new currentBid on that jewelryID
//Then remove the bid using the bidID to complete the process
try{
String emailParam = request.getParameter("email");
int jewelryID = Integer.parseInt(request.getParameter("jewelryID"));
} catch(Exception ex) {
out.println(ex);
}
%>
<a href='customerRepresentativePage.jsp'>Back to Customer Representative Page</a>
</body>
</html>