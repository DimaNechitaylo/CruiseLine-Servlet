<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register</h1>
	<form action = "/CruiseLine-Servlet/main?action=signup" method="post">
		Username<input type="text" name = "username">
		<br\>
		Password<input type="text" name = "password">
		<input type="submit" value="send">
	</form>
</body>
</html>