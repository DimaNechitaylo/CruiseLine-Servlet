<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h1>Login</h1>
	<form action="/CruiseLine-Servlet/main?action=signin" method="post">
		<div class="form-group">
			<label>Username</label> <input type="text" name="username"
				class="form-control">
		</div>
		<div class="form-group">
			<label>Password</label> <input type="password" name="password"
				class="form-control">
		</div>
		<div>
			<c:if test="${not empty wrong_credential}">
     			 ${wrong_credential}
    		</c:if>
		</div>
		<input type="submit" value="send">
	</form>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>