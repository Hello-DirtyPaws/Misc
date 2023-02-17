<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Jewelry</title>
</head>
<body>
<% 
String name = "";
String condition = "";
String color = "";
String type = "";
String brand = "";
String datePosted = "";
String emailOfSeller = "";
String closingDate = "";
String currentBid = "";
int startingPrice = 0;
int increment = 0;
int status = 0;
int jewelryID = Integer.parseInt(request.getParameter("id"));
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "SELECT * FROM jewelry WHERE jewelryID = '" + jewelryID + "';";
ResultSet result = stmt.executeQuery(str);
if(result.next()){
name = result.getString("name");
condition = result.getString("conditionn");
color = result.getString("color");
type = result.getString("type");
brand = result.getString("brand");
datePosted = result.getString("datePosted");
emailOfSeller = result.getString("emailOfSeller");
status = result.getInt("status");
} 
str = "SELECT * FROM infoOfBid WHERE jewelryID = '" + jewelryID + "';";
result = stmt.executeQuery(str);
if(result.next()){
startingPrice = result.getInt("startingPrice");
closingDate = result.getString("closingDate");
increment = result.getInt("increment");
}
str = "SELECT * FROM CurrentBid WHERE jewelryID = '" + jewelryID + "';";
result = stmt.executeQuery(str);
if(result.next()){
currentBid = ""+result.getInt("cost");
			
} else {
currentBid = "No current bids available.";
} 
con.close();
		
%>

<table border = "4">
<tr>
<td>Name: </td><td><%=name %></td>
</tr>
<tr>
<td>Type: </td><td><%=type %></td>
</tr>
<tr>
<td>Brand: </td><td><%=brand %></td>
</tr>
<tr>
<td>Condition: </td><td><%=condition %></td>
</tr>
<tr>
<td>Color: </td><td><%=color %></td>
</tr>
<tr>
<td>Initial Price: </td><td><%=startingPrice %></td>
</tr>
<tr>
<td>Increment: </td><td><%=increment %></td>
</tr>
<tr>
<td>Current Bid: </td><td><%=currentBid %></td>
</tr>
<tr>
<td>Closing Date: </td><td><%=closingDate %></td>
</tr>
<tr>
<td>Email of Seller: </td><td><%=emailOfSeller %></td>
</tr>
</table>
	
<%
out.println("<p><a href='alert.jsp?id="+jewelryID+"'>Click this link to put an alert on this item</a></p>");
if (status == 0)
{
out.println("<p><a href='ifOnlySeller.jsp?id="+jewelryID+"'>Click this link to place a bid</a></p>");
out.println("<p><a href='auto.jsp?id="+jewelryID+"&name="+name+"'>Click this link to put an AUTO bid on this item</a></p>");
out.println("<p><a href='historyBid.jsp?id="+jewelryID+"'>Click this link to see the bid history</a></p>");
out.println("<p><a href='sellerHistory.jsp?id="+emailOfSeller+"'>Click this link to see "+emailOfSeller+"'s history</a></p>");
out.println("<p><a href='similar.jsp?id="+jewelryID+"&type="+type+"&color="+color+"'>Click this link to see similar items</a></p>");
out.println("<p><a href='searchItems.jsp'>Back to searching jewelry</a></p>");
out.println("<p><a href='dash.jsp'>Buyer Dashboard</a></p>");
out.println("<p><a href='sellerDash.jsp'>Seller Dashboard</a></p>");

}
else
{
	out.println("<h2>THIS ITEM'S AUCTION ENDED</h2>");
	out.println("<p><a href='dash.jsp'>Buyer/basic Dashboard</a></p>");
	out.println("<p><a href='sellerDash.jsp'>Seller Dashboard</a></p>");
	out.println("<p><a href='sellerHistory.jsp?id="+emailOfSeller+"'>Click this link to see "+emailOfSeller+"'s history</a></p>");
	out.println("<p><a href='historyBid.jsp?id="+jewelryID+"'>Click this link to see the bid history</a></p>");
	out.println("<p><a href='similar.jsp?id="+jewelryID+"&type="+type+"&color="+color+"'>Click this link to see similar items</a></p>");
	out.println("<p><a href='searchItems.jsp'>Back to searching jewelry</a></p>");

	
}
%>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>