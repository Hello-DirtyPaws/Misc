<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Only Seller</title>
</head>
<body>
<%
String email = ""+session.getAttribute("username");
int jewelryID = Integer.parseInt(request.getParameter("id"));
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "SELECT emailOfSeller FROM jewelry WHERE jewelryID = "+jewelryID;
ResultSet result = stmt.executeQuery(str);
if(result.next()){
if(result.getString("emailOfSeller").equals(email)){
out.println("You cannot place a bid on your own item!");
//	out.print("below are two links. You can either head back to the jewelry page or become a buyer as well!");
out.println("<a href='jewelryPage.jsp?id=" + jewelryID + "'>Cick This To Go Back To The Item</a>");
//out.println("<a href='becomeBuyer.jsp'>Become a buyer as well</a>");
} /* else if(result1.next()) {
out.println("<a href='becomeBuyer.jsp'>Become a buyer as well</a>");
} */ 
else{
con.close();
con = db.getConnection();
stmt = con.createStatement();
str = "SELECT email FROM Buyer WHERE email=" + "'"+email+"';" ;
result = stmt.executeQuery(str);
if(result.next()){
response.sendRedirect("bid.jsp?id="+jewelryID);
}
else{
out.println("<h1>You are not a buyer click the link below to become one!</h1>");
out.println("<p><a href='becomeBuyer.jsp?id="+jewelryID+"'>Become a buyer as well</a></p>");
}
}		
}
%>
<p><a href='dash.jsp'>Click this link for the basic dashboard</a></p>

</body>
</html>