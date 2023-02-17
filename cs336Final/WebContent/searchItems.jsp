<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<title>Search</title>
</head>
<body>
<b>Search for Jewelry</b>
<br>
<br>
<form method="post" action="searchItemsPage.jsp">
<table border="4">
		
<tr>
<td>Filter by this Word</td><td><input type="text" name="thisWord"></td>
</tr>
</table>
<br>
<br>
<b>Filter jewelry by</b>
<br>
<br>
<table border="4">
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
<br>
<b>Choose an option to sort by</b>
<br>
<br>
<table border="4">
<tr>
<td>Sorting By</td><td><select name="sort" size=1>
<option value=""></option>
<option value="type">type</option>
<option value="emailOfSeller">seller</option>
<option value="size">size</option>
<option value="color">color</option>
</select></td>
</tr>
</table>
<br>
<input type="submit" value="Search for Jewelry" class="button">



</form>
<p><a href='dash.jsp'>Back to basic Dashboard</a></p>
<p><a href='logout.jsp'>Log out</a></p>
</body>
</html>