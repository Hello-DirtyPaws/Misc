<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>bidPage</title>
</head>
<body>
	<%
		String email = "" + session.getAttribute("username");
		int jewelryID = Integer.parseInt(request.getParameter("id"));
		int bidParam = Integer.parseInt(request.getParameter("bidAmount"));
		try 
		{
			ApplicationDB db = new ApplicationDB();
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			String str = "INSERT INTO Bid (duration, cost, jewelryID, email) values (?, ?, ?, ?);";
			PreparedStatement ps = con.prepareStatement(str);
			Timestamp duration = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(1, duration);
			ps.setInt(2, bidParam);
			ps.setInt(3, jewelryID);
			ps.setString(4, email);
			ps.executeUpdate();
			
			autoBid(out, jewelryID);
			
			con.close();
		} 
		catch (SQLException ex) 
		{
			//out.println(ex);
			if (ex.getSQLState().equals("20000")) 
			{
				try 
				{
					//out.println("hello");
					ApplicationDB db = new ApplicationDB();
					Connection con = db.getConnection();
					Statement stmt = con.createStatement();
					String str = "delete from Bid where jewelryID=" + jewelryID + " and cost=" + bidParam;
					stmt.execute(str);
					con.close();
					out.println(
							"<p>Your bid must be at least whatever the seller's increment number was plus the current price of the item!</p>");
					out.println(
							"<p>You can go back to the previous item and read the increment and current price</p>");

				} 
				catch (Exception x) 
				{
					out.println(x);
				}
			} 
			else if (ex.getSQLState().equals("10000")) 
			{
				out.println(
						"<p>Your bid must be at least whatever the seller's increment number was plus the starting price of the item!</p>");
				out.println(
						"<p>You can go back to the previous item and read the increment and starting price</p>");
			}
			else 
			{
				out.println(ex + " ** " + ex.getSQLState());
				//autoBid(out, jewelryID);
				
				//YOU HAVE TO LET THE USER PUT IN THE VALID-BID EVEN IF THE ITEM HAS AN AUTO-BID PLACED ON IT. 
				
				
				/*
				try 
				{
					ApplicationDB db = new ApplicationDB();
					Connection con = db.getConnection();
					Statement stmt = con.createStatement();

					String str = "select * from infoOfBid where jewelryID=" + jewelryID;
					ResultSet result1 = stmt.executeQuery(str);
					int increment = 0;
					String e = "";
					if (result1.next()) 
					{
						increment = result1.getInt("increment");
					}
					bidParam = increment + bidParam;
					str = "select * from Auto where jewelryID =" + jewelryID;
					ResultSet result2 = stmt.executeQuery(str);
					if (result2.next())
					{
						e = result2.getString("email");
					}
					str = "UPDATE Bid set cost = " + bidParam + " where jewelryID=" + jewelryID + " and email='" + e
							+ "' order by cost limit 1";
					PreparedStatement ps = con.prepareStatement(str);
					ps.executeUpdate();
					con.close();
				} 
				catch (Exception xx) 
				{
					out.println(xx);
				}
				*/
			}

		}
	%>

<%! 
public void autoBid(JspWriter out, int currJI) throws Exception
{
	int inc = 0;
	int currCost = 0;
	String currEmail = "";
	
	ApplicationDB db = new ApplicationDB();
	Connection con = db.getConnection();
	Statement stmt = con.createStatement();

	//Getting the increment value from db.
	String str = "select * from infoOfBid where jewelryID=" + currJI;
	ResultSet result = stmt.executeQuery(str);
	if (result.next()) 
		inc = result.getInt("increment");
	else
		throw new Exception("ERROR!!! No such bidding item exist in infoOfBid.");
	
	//Getting the email and cost for the current bid on a particular JI.
	str = "select * from CurrentBid where jewelryID = " + currJI;
	result = stmt.executeQuery(str);
	if (result.next()) 
	{
		currCost = result.getInt("cost");
		currEmail = result.getString("email");
	}
	else
		throw new Exception("ERROR!!! No such bidding item exist in CurrentBid.");
	
	
	//got all values needed -> currJI, inc, currCost, currEmail (will update when needed)
	
	String insrt = "";
	str = "Select * from Auto a where a.jewelryID = " + currJI + " and a.max >= " + (currCost+inc);
	result = stmt.executeQuery(str);
	while(result.next())
	{
		result.last();
		int ttlRows = result.getRow();
		result.first();
		
		//if there's only 1 row(1 person) and if that person's bid is the current bid..just break out..without any change;
		if(ttlRows == 1 && result.getString("email").equalsIgnoreCase(currEmail))
		{
			break;
		}
		
		result.previous();
		while(result.next())
		{
			//System.out.println("checking a row in T table.");
			if(!currEmail.equalsIgnoreCase(result.getString("email")) && result.getInt("max") >= (currCost + inc))
			{
				//System.out.println("Updating bid table.");
				currEmail = result.getString("email");
				currCost = currCost + inc;
				
				insrt = "INSERT INTO Bid (duration, cost, jewelryID, email) values (?, ?, ?, ?);";
				PreparedStatement ps = con.prepareStatement(insrt);
				Timestamp duration = new Timestamp(System.currentTimeMillis());
				ps.setTimestamp(1, duration);
				ps.setInt(2, currCost);
				ps.setInt(3, currJI);
				ps.setString(4, currEmail);
				ps.executeUpdate();	
			}
		}
		str = "Select * from Auto a where a.jewelryID = " + currJI + " and a.max >= " + (currCost+inc);
		result = result = stmt.executeQuery(str);
	}	
	
	con.close();
}
%>	

	<p>
		<a href='jewelryPage.jsp?id=<%=jewelryID%>'>Click to go back to
			jewelry item you were looking at</a>
	</p>
	<a href='dash.jsp'>Click to go back to dashboard</a>
</body>
</html>