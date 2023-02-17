<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,java.lang.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Sales Report</title>
</head>
<body>
<h1 align="left">Sales History</h1>
	<%double earnings = 0.0; %>
<form method ="post">
<%
try {
	
	ApplicationDB db = new ApplicationDB();	
	Connection con = db.getConnection();
	Statement stmt = con.createStatement();
	
	String requested = request.getParameter("sales");
	if(requested.equals("1") == true)
	{
		String q = "SELECT SUM(cost) 'Total Earnings' FROM Sold;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next()){%>
			<h3>Total Earnings</h3>
			<table align="center" border="4">
			<tr>
				<th>Total Earnings</th>
			</tr><%
			
				do{%>
				
				<tr>
				<td align = "center"><% out.println(String.format("$%.2f",result.getFloat("Total Earnings")));%></td>
				</tr><%
				}while(result.next());%>
			</table><%
		}
		
		else{
			out.println("NO EARNINGS TO SHOW!");
		}
	}
	
	else if(requested.equals("2") == true)
	{
		String q = "SELECT s.jewelryID, j.brand, j.name, SUM(s.cost) FROM Sold s, jewelry j where j.jewelryID = s.jewelryID GROUP BY s.jewelryID order by SUM(s.cost) DESC;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next()){%>
			<h3>Earnings Per Jewelry</h3>
			<table border ="4" align = "center">
		<tr>
			<th>JID</th>
			
			<th>Brand Name</th>
			
			<th>Jewelry Name</th>
			
			<th>Total Earnings</th>
			
		</tr><%
				do{%>
						<tr>
						<td align = "center"><% out.println(result.getString("s.jewelryID"));%></td>
						<td align = "center"><% out.println(result.getString("j.brand"));%></td>
						<td align = "center"><% out.println(result.getString("j.name"));%></td>
						<td align = "center"><% out.println(String.format("$%.2f",result.getFloat("SUM(s.cost)")));%></td>
						
					</tr><%
				}while(result.next());%>
			</table><%
		}
		
		else{
			out.println("NO ITEMS TO SHOW!");
		}
	}
	
	else if(requested.equals("3") == true)
	{
		String q = "SELECT s.type, SUM(s.cost) FROM Sold s GROUP BY s.type;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next()){%>
			<h3>Earnings Per Types of Jewelry</h3>
			<table align="center" border="4">
			<tr>
				<th>Jewelry Type</th>
				<th>Earnings</th>
			</tr><%
			
			do{%>
				<tr>
					<td align = "center"><% out.println(result.getString("s.type"));%></td>
					<td align = "center"><% out.println(String.format("$%.2f",result.getFloat("SUM(s.cost)")));%></td>
			
				</tr><%
			}while(result.next());%>
			</table><%
		}
		
		else{
			out.println("NO ITEMS TO SHOW!");
		}
	}
	
	else if(requested.equals("4") == true)
	{
		String q = "SELECT email, SUM(cost) FROM Sold GROUP BY email order by email;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next()){%>
			<h3>Earnings Per Seller</h3>
			<table align="center" border="4">
			<tr>
				<th>Seller</th>
				<th>Earnings</th>
			</tr><%
			
			do{%>
				<tr>
					<td align = "center"><% out.println(result.getString("email"));%></td>
					<td align = "center"><% out.println(String.format("$%.2f",result.getFloat("SUM(cost)")));%></td>
			
				</tr><%
			}while(result.next());%>
			</table><%
		}
		
		else{
			out.println("NO ITEMS TO SHOW!");
		}
	}
	
	else if(requested.equals("5") == true)
	{
		String q = "SELECT s.jewelryID,j.name, COUNT(s.color), SUM(s.cost) FROM Sold s, jewelry j where j.jewelryID = s.jewelryID GROUP BY s.jewelryID order by SUM(s.cost) DESC;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next()){%>
		<h3>Best Selling Jewelry</h3>
		<table border ="4" align = "center">
		<tr>
			<th>JID</th>
			
			<th>Jewelry Name</th>
			
			<th>Quantity</th>
			
			<th>Total Earnings</th>
			
		</tr><%
				do{%>
						<tr>
						<td align = "center"><% out.println(result.getString("s.jewelryID"));%></td>
						<td align = "center"><% out.println(result.getString("j.name"));%></td>
						<td align = "center"><% out.println(result.getInt("COUNT(s.color)"));%></td>
						<td align = "center"><% out.println(String.format("$%.2f",result.getFloat("SUM(s.cost)")));%></td>
						
					</tr><%
				}while(result.next());%>
			</table><%
		}
	}
	
	else if(requested.equals("6") == true)
	{
		String q = "SELECT b.email'Buyer_Email', COUNT(b.email)'Bids_Bought', SUM(s.cost)'amount_spent' FROM Buyer b, Sold as s where b.email = s.email GROUP BY Buyer_Email order by amount_spent DESC;";
		ResultSet result = stmt.executeQuery(q);
		if(result.next())
		{%>
		<h3>Best Buyer</h3>
		<table border ="4" align = "center">
		<tr>
			<th>Buyer Email</th>
			
			<th>Number of Bought Bids</th>
			
			<th>Amount spent</th>
			
		</tr><% 
				do 
				{%>
					<tr>
					<td><% out.println(result.getString("Buyer_Email"));%></td>
					
					<td align = "center"><%out.println(result.getInt("Bids_Bought"));%></td>
					
					<td align = "center"><%out.println(String.format("$%.2f",result.getFloat("amount_spent")));%></td>
					
					</tr><%
				} while (result.next());%>
			</table><%
		}
		else{
			out.println("NO EARNINGS TO SHOW!");
		}
		
	}
	else
	{
		out.println("No sales");
	}
	con.close();
}
catch (Exception ex)
{
out.println(ex);
out.println("error");
}
%></form>
<p><br /></p>
<p><br /></p>
<p><a href='administrativeStaffPage.jsp'>Click this link for more sale reports</a></p>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>