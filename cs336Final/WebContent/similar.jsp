<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>Similar Items</title>
<head></head>
<body>
	<h3>Similar Items</h3>
	<h4>similar items are determined by color and type</h4>
	<%
		try {
			ApplicationDB db = new ApplicationDB();
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			int jewelryID = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String color = request.getParameter("color");
			
			String str = "SELECT jewelryID, name, type, color, brand, emailOfSeller FROM jewelry where jewelry.type = '"
					+ type + "'and jewelry.color = '" + color + "';";
			ResultSet result = stmt.executeQuery(str);

			if (result.next()) {
	%>
	<table align="center" border="3">
		<tr>
			<th>JID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Color</th>
			<th>Brand</th>
			<th>Email of Seller</th>
		</tr>
		<%
			do {
		%>

		<tr>
			<td align="center">
				<%
					out.println(result.getInt("jewelryID"));
				%>
			</td>
			<td align="center">
				<%if(jewelryID==result.getInt("jewelryID")){
					out.println(result.getString("name")+"(This is the current item you're viewing)");
					
				}else{
					out.println(result.getString("name"));
				}
				%>
			</td>
			<td align="center">
				<%
					out.println(result.getString("type"));
				%>
			</td>
			<td align="center">
				<%
					out.println(result.getString("color"));
				%>
			</td>
			<td align="center">
				<%
					out.println(result.getString("brand"));
				%>
			</td>
			<td align="center">
				<%
					out.println(result.getString("emailOfSeller"));
				%>
			</td>
		</tr>
		<%
			} while (result.next());
		%>
	</table>
	<%
		} else {
	%>
	<h3>No Items To show</h3>
	<%
		}
			con.close();
		}

		catch (Exception ex) {
			out.println(ex);
			out.println("error");
		}
	%>
	<p>
		<a href='searchItems.jsp'>Back to search</a>
	</p>
	<p>
		<a href='logout.jsp'>Log out</a>
	</p>
</body>
</html>