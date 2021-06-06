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
<link rel="stylesheet" href="webapp/style/style.css">
<jsp:include page="header.jsp" />
<c:set var="order_operation_request">/CruiseLine-Servlet/main?action=order_user_operation&cruise_id=${cruise.id}&order_id=${order.id}</c:set>
</head>

<title>Insert title here</title>
</head>
<body>

	<table class="table table-bordered">
		<tr>
			<th><fmt:message key="view-cruise.table.ship-name"
					bundle="${locale}" /></th>
			<th><c:out value="${cruise.name}" /></th>
		</tr>
		<tr>
			<th><fmt:message key="view-cruise.table.availablecount"
					bundle="${locale}" /></th>
			<th><c:out
					value="${cruise.availableCount}/${cruise.ship.passengerСapacity}" /></th>
		</tr>
		<tr>
			<th><fmt:message key="view-cruise.table.start"
					bundle="${locale}" /></th>
			<th><c:out value="${cruise.start}" /></th>
		</tr>
		<tr>
			<th><fmt:message key="view-cruise.table.finish"
					bundle="${locale}" /></th>
			<th><c:out value="${cruise.finish}" /></th>

		</tr>
		<tr>
			<table class="table">
				<thead>
					<tr>
						<th scope="col"><fmt:message key="view-cruise.table.ports"
								bundle="${locale}" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="portName" items="${cruise.portNames}">
						<tr>
							<td>${portName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</tr>
		<tr>
			<th scope="col"><fmt:message key="view-cruise.table.description"
					bundle="${locale}" /></th>
			<th><c:out value="${order.status}" /></th>
		</tr>
		<tr>
			<th>
			<c:choose>
				<c:when test="${order == null}">
					<form action="${order_operation_request}" method="post">
						<button type="submit" value="submit" name="operation"
							class="btn btn-primary">
							<fmt:message key="order.operation.apply" bundle="${locale}" />
						</button>
						<div></div>
					</form>
				</c:when>
				<c:when test="${order.status == 'PROCESSING'}">
					<label><fmt:message key="order.status.processing"
							bundle="${locale}" /></label>
				</c:when>
				<c:when test="${order.status == 'WATING_PAYMENT'}">
					<fmt:message key="order.status.waiting_payment" bundle="${locale}" />
					<form action="${order.status.waiting_payment}" method="post">
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
				<c:when test="${order.status == 'PAID'}">
					<label><fmt:message key="order.status.paid"
							bundle="${locale}" /></label>
				</c:when>
				<c:when test="${order.status == 'CANCELED'}">
					<label><fmt:message key="order.status.canceled"
							bundle="${locale}" /></label>
				</c:when>
				<c:when test="${order.status == 'REJECTED'}">
					<label><fmt:message key="order.status.rejected"
							bundle="${locale}" /></label>
				</c:when>
				<c:when test="${order.status == 'STARTED'}">
					<label><fmt:message key="order.status.started"
							bundle="${locale}" /></label>
				</c:when>
				<c:when test="${order.status == 'FINISHED'}">
					<label><fmt:message key="order.status.finished"
							bundle="${locale}" /></label>
				</c:when>
			</c:choose>
			</th>
		</tr>
	</table>
</body>
</html>