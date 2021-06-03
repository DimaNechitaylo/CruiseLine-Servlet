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
</head>
<body>
	<h1>Admin page</h1>
	<a
		href="/CruiseLine-Servlet/main?action=get_orders_that_require_processing">orders_that_require_processing</a>
	</td>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Username</th>
				<th scope="col">User id</th>
				<th scope="col">PassengersCount</th>
				<th scope="col">AvailableCount</th>
				<th scope="col">Start</th>
				<th scope="col">Finish</th>
				<th scope="col">Operation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders_that_require_processing}">
				<tr>
					<td>${order.userDto.username}</td>
					<td>${order.userDto.id}</td>
					<td>${order.cruiseDto.passengersCount}</td>
					<td>${order.cruiseDto.availableCount}</td>
					<td>${order.cruiseDto.start}</td>
					<td>${order.cruiseDto.finish}</td>
					<td><c:choose>
							<c:when test="${order.status == 'PROCESSING'}">
								<form action="/CruiseLine-Servlet/main?action=order_admin_operation&order_id=${order.id}"
									method="post">
									<button type="submit" value="confirm" name="operation"
										class="btn btn-primary">Confirm</button>
									<button type="submit" value="reject" name="operation"
										class="btn btn-primary">Reject</button>
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