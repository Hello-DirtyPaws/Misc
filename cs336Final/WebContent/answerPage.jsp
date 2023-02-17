<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>answerPage</title>
</head>
<body>
<%
String email_username = ""+session.getAttribute("username");
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String answer = request.getParameter("answer");
int idforum = Integer.parseInt(request.getParameter("idforum"));
if (answer.isEmpty()){
out.print("<p>Your password or email field was empty. Please fill both of them in.</p>");
} else {
String insertCR = "UPDATE forum set reply ='"+answer+"', cr_email ='"+email_username+"' WHERE idforum='"+idforum+"'";
PreparedStatement ps = con.prepareStatement(insertCR);
ps.executeUpdate();
out.print("<p>You answered the question!</p>");
} 
con.close();
} catch (Exception x) {
	out.print("something went wrong!"+x);
}
%>
<form method="post" action="crForum.jsp">
<input type="submit" value="Back to forum" class="button"><br>
</form>
</body>
</html>

