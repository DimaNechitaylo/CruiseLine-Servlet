<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" var="locale" scope="session" />

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
<link rel="stylesheet" href="style/style.css">
<jsp:include page="header.jsp" />
</head>
<body>
	<h1><fmt:message key="auth.sign-up.title"
					bundle="${locale}" /></h1>
	<form action="/CruiseLine-Servlet/main?action=signup" method="post">
		<div class="form-group">
			<label> <fmt:message key="auth.sign-up.username"
					bundle="${locale}" />
			</label> <input type="text" name="username" class="form-control">
			<c:choose>
				<c:when test="${not empty username_alredy_exist}">
					<fmt:message key="auth.sign-up.error.username_alredy_exist"
						bundle="${locale}" />
				</c:when>
				<c:when test="${not empty invalid_username}">
					<fmt:message key="auth.sign-up.error.invalid_username"
						bundle="${locale}" />
				</c:when>
				<c:otherwise>
					<fmt:message key="auth.sign-up.username.requirements"
						bundle="${locale}" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="form-group">
			<label><fmt:message key="auth.sign-up.password"
					bundle="${locale}" /></label> <input type="password" name="password"
				class="form-control">
			<c:choose>
				<c:when test="${not empty invalid_password}">
					<fmt:message key="auth.sign-up.error.invalid_password"
						bundle="${locale}" />
				</c:when>
				<c:otherwise>
					<fmt:message key="auth.sign-up.password.requirements"
						bundle="${locale}" />
				</c:otherwise>
			</c:choose>
		</div>
		<input type="submit"
			value="<fmt:message key="auth.sign-up.send" bundle="${locale}" />">
		<div></div>
	</form>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>