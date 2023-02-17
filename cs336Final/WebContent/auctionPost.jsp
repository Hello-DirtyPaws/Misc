<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>AP</title>
</head>
<body>
<%
String emailOfSeller = ""+session.getAttribute("username");
String name = request.getParameter("name");
String condition = request.getParameter("condition");
String type = request.getParameter("type");
String brand = request.getParameter("brand");
String size = request.getParameter("size");
String color = request.getParameter("color");
String startingPrice = request.getParameter("startingPrice");
String increment = request.getParameter("increment");
String closingdate = request.getParameter("closingDate");
String secretMinPrice = request.getParameter("secretMinPrice");
Timestamp datePosted = new Timestamp(System.currentTimeMillis());
int status = 0;
String n = null;
/* out.println(startingPrice);
out.println(increment);
out.println(secretMinPrice); */
if(name.isEmpty()||condition.isEmpty() || type.isEmpty()||startingPrice.isEmpty()||closingdate.isEmpty()||increment.isEmpty()||secretMinPrice.isEmpty()||Integer.parseInt(secretMinPrice) < Integer.parseInt(increment)){
out.print("<h1>You did not fill in all the fields or your Secret Minimum Price was less than your increment field. Please go back and try again.</h1>");
}
else if(datePosted.after(Timestamp.valueOf(closingdate.replace("T", " ")+":00"))){
out.print("<h1>You can not post an item's closing date/time before the current date/time. Please go back and retry.</h1>");

	
}
else{
	
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();

Statement stmt = con.createStatement();
String insert = "INSERT INTO jewelry (jewelryID, name, conditionn, type, size, color, brand, datePosted, status, emailOfSeller) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

PreparedStatement ps = con.prepareStatement(insert);
ps.setString(1, n);	
ps.setString(2, name);
ps.setString(3, condition);
ps.setString(4, type);
ps.setString(5,size);
ps.setString(6, color);
ps.setString(7, brand);
ps.setTimestamp(8, datePosted);
ps.setInt(9,status);
ps.setString(10, emailOfSeller);

ps.executeUpdate();
				
String str = "select jewelryID from jewelry where emailOfSeller = '"+ emailOfSeller +"' order by datePosted desc limit 1";

ResultSet result = stmt.executeQuery(str);
if(result.next()){
//	out.println("hi");
int startingPrice1=	Integer.parseInt(startingPrice);
int increment1=	Integer.parseInt(increment);
int secretMinPrice1= Integer.parseInt(secretMinPrice);
	/* out.println(startingPrice1);
	out.println(increment1);
	out.println(secretMinPrice1);
	out.println("hi"); */
int jewelryID = result.getInt("jewelryID");	 
out.println(jewelryID);		
insert="INSERT INTO infoOfBid values (?, ?, ?, ?, ?)";
PreparedStatement ps1 = con.prepareStatement(insert);
ps1.setInt(1, jewelryID);
ps1.setInt(2, startingPrice1);
ps1.setTimestamp(3, Timestamp.valueOf(closingdate.replace("T", " ")+":00"));
ps1.setInt(4, increment1);
ps1.setInt(5, secretMinPrice1);
ps1.executeUpdate();
					
			
}
con.close();
out.print("<p>Your jewelry will now appear in searches.</p>");
} catch (Exception x) {
out.print("Something went wrong. Please go back and try to post your item with different information.");
}
}
%>
<p><a href='sellerDash.jsp'>Click this link to go back to the seller dashboard</a></p>

</body>
</html>