
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>CR Page</title>
</head>
<body>

<%
String email_username = ""+session.getAttribute("username");
out.println("Hello Customer Representative  " + email_username);
%>
<br>
<br>

<table border="4">
<tr><td>Things to do</td></tr>
<%
ApplicationDB db = new ApplicationDB();	
	
Connection con = db.getConnection();
Statement state = con.createStatement();
 	
String str = "select content, fromCol from Email where toCol='CR'";
ResultSet result = state.executeQuery(str);
while(result.next()){
			
String fromCol = result.getString("fromCol");
String content = result.getString("content");
out.print("<tr><th>"+content +"   FROM:   "+  fromCol+ "</th></tr>");
				
}
con.close();
	
%>
</table>
<br>
<br>
<b>Reset Password</b>
<form method="post" action="reset.jsp">

<table border="4">
<tr>
<td>Customer Email:</td><td><input type="text" name="email"></td>
</tr>

<tr>
<td>New Password:</td><td><input type="password" name="password"></td>
</tr>
</table>
<br>
<input type="submit" value="submit" class="button">
</form>

<br>
<br>
<b>Remove a Bid</b>
<form method="post" action="remove.jsp">
<table border="4">
<tr>
<td>ID of Jewelry:</td><td><input type="text" name="jewelryID"></td>
</tr>

<tr>
<td>Customer Email:</td><td><input type="text" name="email"></td>
</tr>
</table>

<br>
<input type="submit" value="submit" class="button">
</form>


<br>

<b>Remove Illegal Auction</b>
<form method="post" action="auctionRemove.jsp">
<table border="4">
<tr>
<td>ID of Jewelry:</td><td><input type="text" name="jewelryID"></td>
</tr>

</table>

<br>
<input type="submit" value="submit" class="button">
</form>


<p><a href=crForum.jsp>Click this link to answer questions on the forum</a></p>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>


