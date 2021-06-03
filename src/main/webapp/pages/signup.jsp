<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<jsp:include page="header.jsp" />
</head>
<body>
<h1>Register</h1>
	<form action = "/CruiseLine-Servlet/main?action=signup" method="post">
		Username<input type="text" name = "username">
		<br\>
		Password<input type="password" name = "password">
		<input type="submit" value="send">
	</form>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>