<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

${err}
	<form action="./login.do" method="post">
		Username: <input type="text" name="username">
		Password: <input type="password" name="password">
		<input type="submit" value="login">
	</form>
</body>
</html>