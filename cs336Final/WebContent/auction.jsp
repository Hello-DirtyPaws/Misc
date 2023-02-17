<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>auction</title>
</head>
<body>
<h1>Post an Item!</h1>

<form method="post" action="auctionPost.jsp">
<table border="4">


<tr>
<td>Starting Price</td><td><input type="number" name="startingPrice" min="0"></td>
</tr>

<tr>
<td>Increment</td><td><input type="number" name="increment" min="0"></td>
</tr>

<tr>
<td>Closing date/time</td><td><input type="datetime-local"  name="closingDate"></td>
</tr>

<tr>
<td>Secret Minimum price</td><td><input type="number" name="secretMinPrice" min="0"></td>
</tr>

<tr>
<td>Jewelry name</td><td><input type="text" name="name"></td>
</tr>

<tr>
<tr>
<td>Jewelry brand</td><td><input type="text" name="brand"></td>
</tr>
<tr>
<td>Type</td><td><select name=type size=1>
<option value=""></option>
<option value="ring">ring</option>
<option value="necklace">necklace</option>
<option value="earring">earring</option>
</select></td>
</tr>
	
	
<tr>
<td>Condition</td><td><select name=condition size=1>
<option value=""></option>
<option value="New">New</option>
<option value="Used">Used</option>
</select></td>
</tr>

	
<tr>
<td>Size</td><td><select name="size" size=1>
<option value=""></option>
<option value="Large">Large</option>
<option value="Medium">Medium</option>
<option value="Small">Small</option>
<option value="XL">XL</option>
<option value="XS">XS</option>
</select></td>
</tr>

<tr>
<td>Color</td><td><select name="color" size=1>
<option value=""></option>
<option value="black">black</option>
<option value="green">green</option>
<option value="orange">orange</option>
<option value="teal">teal</option>
<option value="red">red</option>
<option value="blue">blue</option>
<option value="grey">grey</option>
<option value="purple">purple</option>
<option value="white">white</option>
</select>
</td>
</tr>
	




</table>
<br>
<input class="button" type="submit" value="Post item to the auction page!">
</form>
<p><a href='sellerDash.jsp'>Click this link to go back to the seller dashboard</a></p>

<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>