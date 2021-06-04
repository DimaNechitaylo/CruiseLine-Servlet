<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<c:set var="order_operation_request">/CruiseLine-Servlet/main?action=order_user_operation&cruise_id=${cruise.id}&order_id=${order.id}</c:set>
</head>

<title>Insert title here</title>
</head>
<body>

	<table class="table table-bordered">
		<tr>
			<th>id</th>
			<th><c:out value="${cruise.id}" /></th>
		</tr>
		<tr>
			<th>Cruise names</th>
			<th><c:out value="${cruise.name}" /></th>
		</tr>
		<tr>
			<th>Ship name</th>
			<th><c:out value="${cruise.ship.name}" /></th>
		</tr>
		<tr>
			<th>availableCount</th>
			<th><c:out
					value="${cruise.availableCount}/${cruise.ship.passengerСapacity}" /></th>
		</tr>
		<tr>
			<th>Start</th>
			<th><c:out value="${cruise.start}" /></th>
		</tr>
		<tr>
			<th>Finish</th>
			<th><c:out value="${cruise.finish}" /></th>

		</tr>
		<tr>
			<th>Status</th>
			<th><c:out value="${order.status}" /></th>

		</tr>
		<tr>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Ports</th>
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
			<c:choose>
				<c:when test="${order == null}">
					<form action="${order_operation_request}" method="post">
						<button type="submit" value="submit" name="operation"
							class="btn btn-primary">RequestAnOrder</button>
						<div></div>
					</form>
				</c:when>
				<c:when test="${order.status == 'PROCESSING'}">
					<label>Wait for confirmation</label>
				</c:when>
				<c:when test="${order.status == 'WATING_PAYMENT'}">
					<form action="${order_operation_request}" method="post">
						<button type="submit" value="pay" name="operation"
							class="btn btn-primary">Pay</button>
						<button type="submit" value="cancel" name="operation"
							class="btn btn-primary">Cancel</button>
					</form>
				</c:when>
				<c:otherwise>
					<c:out value="${order.status}" />
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</body>
</html>