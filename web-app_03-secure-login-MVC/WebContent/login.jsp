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
	<form action="./loginPage" method="post">
		UserId: <input type="text" name="userId"/>
		Password: <input type="password" name="password"/>
		<input type="submit" value="login">
	</form>
</body>
</html>