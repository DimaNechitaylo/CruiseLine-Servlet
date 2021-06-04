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
	<h1>Register</h1>
	<form action="/CruiseLine-Servlet/main?action=signup" method="post">
		<div class="form-group">
			<label>Username</label> <input type="text" name="username"
				class="form-control">
			<c:choose>
				<c:when test="${not empty username_alredy_exist}">
     			 ${username_alredy_exist}
    			</c:when>
				<c:when test="${not empty invalid_username}">
     			 ${invalid_username}
    			</c:when>
				<c:otherwise>
    			 Username must meet requirements: -------
     			</c:otherwise>
			</c:choose>
		</div>
		<div class="form-group">
			<label>Password</label> <input type="password" name="password"
				class="form-control">
			<c:choose>
				<c:when test="${not empty invalid_password}">
     			 ${invalid_password}
    			</c:when>
				<c:otherwise>
    			 password must meet requirements: -------
     			</c:otherwise>
			</c:choose>
		</div>
		<input type="submit" value="send">
		<div></div>
	</form>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>