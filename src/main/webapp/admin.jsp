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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/style.css">
<jsp:include page="header.jsp" />
</head>
<body>
	<h2><fmt:message key="admin.orders_that_require_processing.title" bundle="${locale}" /></h2>
	</td>
	<table class="table">
		<thead>
			<tr>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.username" bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.cruisename" bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.passengerscount" bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.availablecount" bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.start" bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="admin.orders_that_require_processing.table.finish" bundle="${locale}" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders_that_require_processing}">
				<tr>
					<td>${order.userDto.username}</td>
					<td>${order.cruiseDto.name}</td>
					<td>${order.cruiseDto.passengersCount}</td>
					<td>${order.cruiseDto.availableCount}</td>
					<td>${order.cruiseDto.start}</td>
					<td>${order.cruiseDto.finish}</td>
					<td><c:choose>
							<c:when test="${order.status == 'PROCESSING'}">
								<form action="/CruiseLine-Servlet/main?action=order_admin_operation&order_id=${order.id}"
									method="post">
									<button type="submit" value="confirm" name="operation"
										class="btn btn-primary"><fmt:message key="order.operation.confirm" bundle="${locale}" /></button>
									<button type="submit" value="reject" name="operation"
										class="btn btn-primary"><fmt:message key="order.operation.reject" bundle="${locale}" /></button>
								</form>
							</c:when>
							<c:otherwise>
								<c:out value="${order.status}" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>