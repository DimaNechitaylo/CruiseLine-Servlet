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
	<a href="/CruiseLine-Servlet/main?action=user_orders"><fmt:message
			key="profile.orders.title" bundle="${locale}" /> </a>
	<table class="table">
		<thead>
			<tr>
				<th scope="col"><fmt:message key="profile.orders.table.name"
						bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="profile.orders.table.start"
						bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="profile.orders.table.finish"
						bundle="${locale}" /></th>
				<th scope="col"><fmt:message key="profile.orders.table.status"
						bundle="${locale}" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="userOrder" items="${userOrsers}">
				<tr>
					<td><a href="/CruiseLine-Servlet/main?action=view_cruise&cruise_id=${userOrder.cruiseDto.id}">${userOrder.cruiseDto.name}</a></td>
					<td>${userOrder.cruiseDto.start}</td>
					<td>${userOrder.cruiseDto.finish}</td>
					<td><c:choose>
							<c:when test="${userOrder == null}">
								<form
									action="/CruiseLine-Servlet/main?action=order_user_operation&order_id=${userOrder.id}"
									method="post">
									<button type="submit" value="submit" name="operation"
										class="btn btn-primary">RequestAnOrder</button>
									<div></div>
								</form>
							</c:when>
							<c:when test="${userOrder.status == 'PROCESSING'}">
								<label><fmt:message key="order.status.processing"
										bundle="${locale}" /></label>
							</c:when>
							<c:when test="${userOrder.status == 'WATING_PAYMENT'}">
								<fmt:message key="order.status.waiting_payment"
									bundle="${locale}" />
								<form
									action="/CruiseLine-Servlet/main?action=order_user_operation&order_id=${userOrder.id}"
									method="post">
									<button type="submit" value="pay" name="operation"
										class="btn btn-primary">
										<fmt:message key="order.operation.pay" bundle="${locale}" />
									</button>
									<button type="submit" value="cancel" name="operation"
										class="btn btn-primary">
										<fmt:message key="order.operation.cancel" bundle="${locale}" />
									</button>
								</form>
							</c:when>
							<c:when test="${userOrder.status == 'PAID'}">
								<label><fmt:message key="order.status.paid"
										bundle="${locale}" /></label>
							</c:when>
							<c:when test="${userOrder.status == 'CANCELED'}">
								<label><fmt:message key="order.status.canceled"
										bundle="${locale}" /></label>
							</c:when>
							<c:when test="${userOrder.status == 'REJECTED'}">
								<label><fmt:message key="order.status.rejected"
										bundle="${locale}" /></label>
							</c:when>
							<c:when test="${userOrder.status == 'STARTED'}">
								<label><fmt:message key="order.status.started"
										bundle="${locale}" /></label>
							</c:when>
							<c:when test="${userOrder.status == 'FINISHED'}">
								<label><fmt:message key="order.status.finished"
										bundle="${locale}" /></label>
							</c:when>
						</c:choose>
					<td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>

<footer>
	<jsp:include page="footer.jsp" />
</footer>
</html>