<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" var="locale" scope="session" />

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/style.css">
</head>
<body>
	<div id="header" class="container">
		<div class="row">
			<div class="col-lg-9">
				<h1>
					<a href="/CruiseLine-Servlet/main?action=main"> <img
						class="icon" src="assets/cruise-liner.png"> Cruise Line
					</a>
				</h1>
			</div>
			<div class="col-lg-3">
				<div class="dropdown show">
					<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
						id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <c:if test="${lang eq 'uk'}">
							<img width="30px;" class="mr-2"
								src="https://www.countryflags.io/ua/flat/64.png">
						</c:if> <c:if test="${lang eq 'en'}">
							<img width="30px;" class="mr-2"
								src="https://www.countryflags.io/gb/flat/64.png">
						</c:if>
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item"
							href="/CruiseLine-Servlet/main?action=change_lang&lang=uk"><img
							width="50px;" class="mr-2"
							src="https://www.countryflags.io/ua/flat/64.png"> </a> <a
							class="dropdown-item"
							href="/CruiseLine-Servlet/main?action=change_lang&lang=en"><img
							width="50px;" class="mr-2"
							src="https://www.countryflags.io/gb/flat/64.png"> </a>
					</div>
				</div>
				<c:choose>
					<c:when test="${user == null}">
						<a href="/CruiseLine-Servlet/signup.jsp" class="sign-up"> <fmt:message
								key="header.auth.sign-up" bundle="${locale}" />
						</a>
						</br>
						<a href="/CruiseLine-Servlet/signin.jsp" class="sign-in"> <fmt:message
								key="header.auth.sign-in" bundle="${locale}" />
						</a>
					</c:when>
					<c:when test="${user != null}">
						<a href="/CruiseLine-Servlet/main?action=logout" class="logout">
							<fmt:message key="header.auth.logout" bundle="${locale}" />
						</a>
						<a href="/CruiseLine-Servlet/main?action=profile"><h2>
								<fmt:message key="header.profile" bundle="${locale}" />
								<c:out value="${user.username}" />
							</h2></a>
						<c:if test="${user.role == 'ADMIN'}">
							<a
								href="/CruiseLine-Servlet/main?action=get_orders_that_require_processing">
								<fmt:message key="header.admin" bundle="${locale}" />
							</a>
						</c:if>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>