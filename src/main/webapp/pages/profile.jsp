<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>HOME</title>
<jsp:include page="header.jsp" />
</head>
<body>
	Profile
	<c:out value="${user.username}" />

	<a href="/CruiseLine-Servlet/main?action=user_orders">Yours orders:
	</a>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="userOrder" items="${userOrsers}">
				<tr>
					<td>${userOrder.cruiseDto.name}</td>
					<td>${userOrder.cruiseDto.start}</td>
					<td>${userOrder.cruiseDto.finish}</td>
					<td>${userOrder.status}</td>
					<td><c:choose>
							<c:when test="${userOrder == null}">
								<form action="/CruiseLine-Servlet/main?action=order_operation"
									method="post">
									<button type="submit" value="submit" name="operation"
										class="btn btn-primary">RequestAnOrder</button>
									<div></div>
								</form>
							</c:when>
							<c:when test="${userOrder.status == 'PROCESSING'}">
								<label>Wait for confirmation</label>
							</c:when>
							<c:when test="${userOrder.status == 'WATING_PAYMENT'}">
								<form action="/CruiseLine-Servlet/main?action=order_operation"
									method="post">
									<button type="submit" value="pay" name="operation"
										class="btn btn-primary">Pay</button>
									<button type="submit" value="cancel" name="operation"
										class="btn btn-primary">Cancel</button>
								</form>
							</c:when>
							<c:otherwise>
								<c:out value="${userOrder.status}" />
							</c:otherwise>
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