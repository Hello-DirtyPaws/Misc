<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Information Display</title>
</head>
<body>

Hello, ${username}! <br>
You are a ${prof}. <br>
And you are currently located in ${location}.<br>

You logged in at ${time}.<br>
Current time is <%=new Date()%><br>

The default user1: ${defaultUser1}.<br>
The default user2: ${defaultUser2}.<br>

<form method="get" action="./login.do">
	<input type="submit" value="logout">
</form>


</body>
</html>