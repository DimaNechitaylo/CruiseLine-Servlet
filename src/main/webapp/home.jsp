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
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<h2>
					<fmt:message key="main.filter.title" bundle="${locale}" />
				</h2>
				<form action="/CruiseLine-Servlet/main?action=filter&page=1"
					method="post">
					<div class="form-group">
						<label for="validationServer01"> <fmt:message
								key="main.filter.date" bundle="${locale}" />

						</label> <input type="date" name="date" class="form-control">

					</div>
					<fmt:message key="main.filter.duration.title" bundle="${locale}" />
					<div class="form-group">
						<label for="validationServer01"> <fmt:message
								key="main.filter.duration.min" bundle="${locale}" />
						</label> <input type="text" name="min_duration" class="form-control">
					</div>
					<div class="form-group">
						<label for="validationServer01"> <fmt:message
								key="main.filter.duration.max" bundle="${locale}" />
						</label> <input type="text" name="max_duration" class="form-control">
					</div>
					<div>
						<c:if test="${not empty invalid_duration}">
							<fmt:message key="main.filter.duration.error" bundle="${locale}" />
						</c:if>
					</div>
					<div class="form-group">
						<input type="submit"
							value="<fmt:message key="main.filter.btn" bundle="${locale}" />">
					</div>

				</form>
			</div>
			<div class="col-md-8">
				<a href="/CruiseLine-Servlet/main?action=get_cruises&page=1"><h2>
						<fmt:message key="main.cruiselist.title" bundle="${locale}" />
					</h2></a> <a><h4>
						<c:if
							test="${(not empty date) or (not empty min_duration) or (not empty max_duration)}">
							<fmt:message key="main.cruiselist.filter_parameters"
								bundle="${locale}" />
							<c:if test="${not empty date}">
								<fmt:message key="main.filter.date" bundle="${locale}" />
								<a>- </a> ${date} 
								</c:if>
							<c:if test="${not empty min_duration}">
								<fmt:message key="main.filter.duration.min" bundle="${locale}" />
								<a>- </a> ${min_duration} 
								</c:if>
							<c:if test="${not empty max_duration}">
								<fmt:message key="main.filter.duration.max" bundle="${locale}" />
								<a>- </a> ${max_duration}
								</c:if>
						</c:if>
					</h4></a>
				<table class="table">
					<thead>
						<tr>
							<th scope="col"><fmt:message
									key="main.cruiselist.table.name" bundle="${locale}" /></th>
							<th scope="col"><fmt:message
									key="main.cruiselist.table.availablecount" bundle="${locale}" /></th>
							<th scope="col"><fmt:message
									key="main.cruiselist.table.start" bundle="${locale}" /></th>
							<th scope="col"><fmt:message
									key="main.cruiselist.table.finish" bundle="${locale}" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cruise" items="${cruises}">
							<tr>
								<td><a
									href="/CruiseLine-Servlet/main?action=view_cruise&cruise_id=${cruise.id}">${cruise.name}</a></td>
								<td>${cruise.availableCount}</td>
								<td>${cruise.start}</td>
								<td>${cruise.finish}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id = "pages" class="row">
				<div class="col-sm-12">
					<c:forEach var="page" items="${pages}">
						<tr>
							<td><a
								href="/CruiseLine-Servlet/main?action=get_cruises&page=${page}">${page}</a></td>
						</tr>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>
