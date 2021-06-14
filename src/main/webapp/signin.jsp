<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" var="locale" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/style.css">
<jsp:include page="header.jsp" />
</head>
<body>
	<h1><fmt:message key="auth.sign-in.title" bundle="${locale}" /></h1>
	<form action="/CruiseLine-Servlet/main?action=signin" method="post">
		<div class="form-group">
			<label><fmt:message key="auth.sign-in.username" bundle="${locale}" /></label> <input type="text" name="username"
				class="form-control">
		</div>
		<div class="form-group">
			<label><fmt:message key="auth.sign-in.password" bundle="${locale}" /></label> <input type="password" name="password"
				class="form-control">
		</div>
		<div>
			<c:if test="${not empty wrong_credential}">
			<fmt:message key="auth.sign-in.error.wrong_credential" bundle="${locale}" />    		
			</c:if>
		</div>
		<input type="submit" value="<fmt:message key="auth.sign-in.send" bundle="${locale}" />">
	</form>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>