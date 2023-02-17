<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>RP</title>
</head>
<body>
<%
try {
ApplicationDB db = new ApplicationDB();	
Connection con = db.getConnection();
Statement stmt = con.createStatement();
String firstParam = request.getParameter("firstName");
String lastParam = request.getParameter("lastName");
String emailParam = request.getParameter("email");
String passwordParam = request.getParameter("password");
String passwordConfirmParam = request.getParameter("passwordConfirm");
String sellerParam = request.getParameter("seller"); 
if (emailParam.isEmpty() || passwordParam.isEmpty()){
out.print("<h1>Password or email field was empty. Please fill both in.</h1>");
} 
else if (passwordParam.equals(passwordConfirmParam)) {
String insert1 = "INSERT INTO customer values (?, ?, ?, ?);";
PreparedStatement ps = con.prepareStatement(insert1);
ps.setString(1, emailParam);
ps.setString(2, firstParam);
ps.setString(3, lastParam);
ps.setString(4, passwordParam);
//ps.setString(5, sellerParam);
out.print(sellerParam);
ps.executeUpdate();
				
if(sellerParam.equals("yes")){
String insert2 = "INSERT INTO Seller values (?);";
PreparedStatement ps1 = con.prepareStatement(insert2);
ps1.setString(1, emailParam);
//ps.setString(5, sellerParam);
//out.print(sellerParam);
ps1.executeUpdate();
	}	
else{
String insert3 = "INSERT INTO Buyer values (?);";
PreparedStatement ps2 = con.prepareStatement(insert3);
ps2.setString(1, emailParam);
//ps.setString(5, sellerParam);
//out.print(sellerParam);
ps2.executeUpdate();
					
}
				
out.print("<p>Customer account has now been created</p>");
response.sendRedirect("index.jsp");
} 
else {
out.print("<h1>Password does not match confirm password field</h1>");
}	
			
con.close();
			
} catch (Exception x) {
out.print("<h1>You entered an invalid email OR your passwword or email were too many characters. Please try again</h1>");
}
%>
<form method="post" action="register.jsp">
<input type="submit" value="Back to register page" class="button">
</form>
<br>
<form method="post" action="index.jsp">
<input type="submit" value="Back to main page" class="button">
</form>
</body>
</html>