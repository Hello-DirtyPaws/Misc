<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Insert title here</title>
</head>
<body>
<h1>Welcome <%out.print(session.getAttribute("username")); %></h1>
<b>FORUM PAGE FOR QUESTIONS!</b>
<br>
<br>
<table border='4'>
<tr>
<th>Customer Rep Email</th>
<th>Customer Email</th>
<th>Subject</th>
<th>Question</th>
<th>Answer</th>
<th>Date Posted</th>
</tr>
<% 
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String str = "SELECT f.cr_email, f.customer_email, f.subject, f.question, f.reply, f.datePosted FROM forum as f;";
ResultSet result = stmt.executeQuery(str);
while(result.next()){
String cr_email= result.getString("f.cr_email");
String customer_email= result.getString("f.customer_email");
String subject = result.getString("f.subject");
String question = result.getString("f.question");
String reply = result.getString("f.reply");
String datePosted = result.getString("f.datePosted");
if(cr_email ==null){
out.print("<tr><th> No Customer Rep has answered this yet!</th>");
}
else{
out.print("<tr><th>" +cr_email+ "</th>");
}
out.print("<th>" +customer_email+ "</th>");
out.print("<th>" + subject +"</th>");
out.print("<th>"+ question + "</th>");
if(reply ==null){
out.print("<th> This has not been answered yet!</th>");
}
else{
out.print("<th>"+ reply + "</th>");
}
out.print("<th>"+ datePosted + "</th></tr>");
				
}
con.close();
%>
</table>
<br>
<br>
<a href=dash.jsp>Back to basic/buyer dashboard</a>
<a href=sellerDash.jsp>Back to seller dashboard</a>
<p><a href='logout.jsp'>Log out</a></p>

</body>
</html>

