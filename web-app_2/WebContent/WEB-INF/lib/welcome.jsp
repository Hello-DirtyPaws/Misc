<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	Welcome ${username}.
	<form action="./moreinfo" method="post">
		Profession: 
		<input type="radio" name="prof" value="Java Developer">Java Developer
		<input type="radio" name="prof" value="Software Engineer">Software Engineer
		
		<br>
		Select your current location:
		<select name="location">
			<option value="India">India</option>
			<option value="USA">USA</option>
			<option value="China">China</option>
		</select>
		<input type="submit">
	</form>
</body>
</html>