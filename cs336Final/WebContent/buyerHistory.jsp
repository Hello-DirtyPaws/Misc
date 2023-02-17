<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Buyer's History</title>
</head>
<body>
<h2>Buyer History!</h2>

<table border='4'>
<tr>
<th>BID</th>
<th>JID</th>
<th>Jewelry Name</th>
</tr>
<%
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();

Statement stmt = con.createStatement();

String email_username = ""+session.getAttribute("username"); //"jack@gmail.com";
out.println(email_username);


String str = "SELECT b.BidID, b.jewelryID, j.name FROM Bid as b, jewelry as j WHERE email=" + "'"+email_username+"' and b.jewelryID = j.jewelryID;" ;

ResultSet result = stmt.executeQuery(str);

if(result != null){
while(result.next()){
		
String bID= result.getString("b.BidID");

String jID = result.getString("b.jewelryID");

String jName = result.getString("j.name");
	
out.print("<th> "+ bID + "</th>");

out.print("<th> "+ jID + "</th>");
out.print("<th>"+ jName + "</th></tr>");
}
			
con.close();
}

}


catch (Exception x) {
out.println("The buyer's history who you are attempting to view has deleted his account. His information is no longer viewable to other customers.");

}
%>
</table>
<p><a href='dash.jsp'>Click this link for the basic dashboard</a></p>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>