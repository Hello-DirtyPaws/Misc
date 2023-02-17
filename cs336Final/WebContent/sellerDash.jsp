<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Seller dash</title>
</head>
<body>
<h1>Seller Dashboard</h1>
<%
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String email_username = ""+session.getAttribute("username"); //"jack@gmail.com";
String str = "SELECT email FROM Seller WHERE email=" + "'"+email_username+"';" ;
ResultSet result = stmt.executeQuery(str);
if(result.next()){
String email = result.getString("email");
con.close();
if(email.equals(email_username)){
out.println("<h2>Welcome "+email_username+"! Sell some items please!</h2>");
out.println("<br>");
out.println("<br>");
out.println("<b>Items you have put up for auction</b>");
out.println("<br>");
out.println("<br>");
out.println("<table border='4'><tr><th>Jewelry Name</th><th>Current Bidding Amount $</th><th>Closing Date/Time</th></tr>");
con = db.getConnection();
stmt = con.createStatement();
str = "SELECT j.name, j.jewelryID, iob.closingDate FROM infoOfBid as iob join jewelry as j on j.jewelryID = iob.jewelryID WHERE emailOfSeller = '" + email_username + "';";
result = stmt.executeQuery(str);
while(result.next()){
	
int id= result.getInt("j.jewelryID");
String name = result.getString("j.name");
String closingDate = result.getString("iob.closingDate");
String str2 = "SELECT cb.cost from CurrentBid as cb join jewelry as j on cb.jewelryID = j.jewelryID WHERE j.jewelryID = " + id;
String currentCost = "This item has no bids";
Statement stmt2 = con.createStatement();
ResultSet result2 = stmt2.executeQuery(str2);
	
if(result2.next()){
	
currentCost = result2.getString("cb.cost");
} 
out.print("<tr><th><a href='jewelryPage.jsp?id=" + id + "'>" +name + "</a></th>");
out.print("<th> $  "+ currentCost + "</th>");
out.print("<th>"+ closingDate + "</th></tr>");
}
out.print("</table>");
con.close();
%>
<br>
<br>
<b>EMAILS FROM BUYME</b>
<br>
<br>
<table border='4'>
<tr>
<td>Type Of notification</td>
<td>ID of jewelry</td>
<td>Content</td>
<td>Time</td>
</tr>
<%
Connection con1 = db.getConnection();
Statement stmt3 = con1.createStatement();
	 
str = "SELECT name, id, content, timeOF FROM auctionInfo WHERE email='"+session.getAttribute("username")+"'";
ResultSet result2 = stmt3.executeQuery(str);
while(result2.next()){
String name = result2.getString("name");
String id = result2.getString("id");
String content = result2.getString("content");
String time = result2.getString("timeOF");
out.print("<tr><th>" +name + "</th>");
out.print("<th>" + id +"</th>");
out.print("<th>"+content + "</th>");
out.print("<th>"+time+"</th></tr>");    
}
%>
</table>
<br>
<br>
<%

out.println("<p><a href='auction.jsp'>Put an item up for auction!</a><p>");
			
con1.close();
}
}
else{
out.println("<h1>Sorry you do not have access to this page unless you become a seller</h1>");
out.println("<p><a href='becomeSeller.jsp'>Click this to become a seller as well</a></p>");
	
}
}
catch (Exception ex) {
out.println(ex);
out.println("error");
}
%>

<p><a href='dash.jsp'>Click this link for the basic dashboard</a></p>
<p><a href='sellerHistory.jsp'>Click to see your seller history</a></p>
<p><a href='question.jsp'>Click this link to ask a question</a></p>
<p><a href='forum.jsp'>Click this link to view questions asked</a></p>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>
